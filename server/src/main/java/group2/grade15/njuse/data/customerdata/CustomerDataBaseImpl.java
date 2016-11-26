package group2.grade15.njuse.data.customerdata;


import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.sql.Date;

/**
 * Created by George on 2016/11/13.
 */
public class CustomerDataBaseImpl implements CustomerDataService {
    private DatabaseMySql mySql=null;
    private Connection customerConnection=null;

    public CustomerDataBaseImpl(DatabaseInfo info) throws RemoteException{
        mySql=new DatabaseMySql(info);
        customerConnection=mySql.init();
    }

    /**
     * @param customerId
     * @return CustomerPO
     * @throws RemoteException
     * 获取customer信息
     */
    public CustomerPO getCustomer(int customerId) throws RemoteException {
        if(customerConnection==null){
            customerConnection=mySql.init();
        }

        int id=0;
        String name=null,password=null,contact=null;
        Date birthday=null;
        double credit=0.00;
        MemberType type=null;
        try{
            Statement getInfo=customerConnection.createStatement();
            /*if(getInfo==null){
                System.out.print("wrong");
            }*/
            ResultSet r=getInfo.executeQuery(
                    "select * from Customer where customerID = "+customerId);
            if(r.next()) {
                id = customerId;
                password = r.getString(2);
                name = r.getString(3);
                contact = r.getString(4);
                credit = r.getFloat(5);
                type = MemberType.values()[r.getInt(6)];
                birthday=r.getDate(7);
            }
            getInfo.close();
            customerConnection.close();
            customerConnection=null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        CustomerPO customerInfo=new CustomerPO(id,name,password,contact,birthday,credit,type);
        return customerInfo;
    }

    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException
     * 仅name，password，contact，birthday，type可进行添加，birthday和type一经添加不可修改
     */
    public CustomerPO add(CustomerPO po) throws RemoteException {
        if(customerConnection==null){
            customerConnection=mySql.init();
        }

        int id=0;
        String name=po.getName(),password=po.getPassword(),contact=po.getContact();
        Date birthday=po.getBirthday();
        MemberType type=po.getType();
        double credit=0.00;

        try{
            Statement makeID=customerConnection.createStatement();
            ResultSet newID=makeID.executeQuery("select max(customerid) from customer");
            if(newID.next()){
                id=newID.getInt(1)+1;
            }
            else{
                throw new SQLException();
            }
            makeID.close();

            PreparedStatement addOne=customerConnection.prepareStatement("insert into customer values(?,?,?,?,DEFAULT,?,?)");
            addOne.setInt(1,id);
            addOne.setString(2,password);
            addOne.setString(3,name);
            addOne.setString(4,contact);
            addOne.setInt(5,type.ordinal());
            addOne.setDate(6,birthday);
            addOne.executeUpdate();
            addOne.close();

            Statement initCredit=customerConnection.createStatement();
            ResultSet newCredit=initCredit.executeQuery("select credit from customer where customerid = "+id);
            if(newCredit.next()){
                credit=newCredit.getFloat(1);
            }
            else{
                throw new SQLException();
            }

            customerConnection.close();
            customerConnection=null;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        CustomerPO newCustomer=new CustomerPO(id,name,password,contact,birthday,credit,type);
        return newCustomer;
    }

    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException
     * 只能更改name，password，telnum，bl层在申请更改后应立即调用get方法更新信息
     */
    public ResultMessage modify(CustomerPO po) throws RemoteException {
        if(customerConnection==null){
            customerConnection=mySql.init();
        }

        try{
            PreparedStatement modify=customerConnection.prepareStatement("update customer set name = ?,password = ?,telnum= ? " +
                    "where customerid = "+po.getId());
            modify.setString(1,po.getName());
            modify.setString(2,po.getPassword());
            modify.setString(3,po.getContact());
            modify.executeUpdate();
            modify.close();

            customerConnection.close();
            customerConnection=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }

    }
}
