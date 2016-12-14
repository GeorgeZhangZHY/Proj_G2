package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.bl.promotionfactory.HotelPromotionBL;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Date;

/**
 * Created by Guo on 2016/12/6.
 */
public class BirthdayHotelPromotion implements HotelPromotionBL{
    public OrderBL order;

    public BirthdayHotelPromotion(){
        order = new Order();
    }

    @Override
    public double countPrice(OrderVO orderVO, HotelPromotionVO hotelPromotionVO) {

        double totalPrice = order.getOriginalPrice(orderVO);

        if(isFit(orderVO, hotelPromotionVO)) {
            return totalPrice * hotelPromotionVO.getDiscount();
        } else {
            return totalPrice;
        }
    }

    private boolean isFit(OrderVO orderVO, HotelPromotionVO hotelPromotionVO){
        Date checkInTime = orderVO.getCheckInTime();

        //获取客户的生日
        int customerID = orderVO.getCustomerID();
        Date birthday = null;
        try {
           birthday = RemoteHelper.getInstance().getCustomerDataService().getCustomer(customerID).getBirthday();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(birthday == null){
            return false;
        }

        if(checkInTime.compareTo(birthday) == 0){
            return true;
        } else {
            return false;
        }
    }
}
