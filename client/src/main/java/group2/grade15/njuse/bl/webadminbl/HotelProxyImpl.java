package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.CustomerVO;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

public class HotelProxyImpl implements HotelProxyBL{

	public ResultMessage createHotel(HotelVO hotel){

        ResultMessage result;

        //将vo转化为po
        HotelPO po = this.hotelVOToPO(hotel);

        try {
            RemoteHelper.getInstance().getHotelPartService().addHotel(po);
            result = ResultMessage.SUCCESS;
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.FAILED;
        }

        return result;
	}

	public HotelListVO getHotelList(){
        HotelListVO vo;
        ArrayList<HotelPO> hotelPOList = null;
        ArrayList<HotelVO> hotelList = null;

        try {
            hotelPOList = RemoteHelper.getInstance().getHotelPartService().getHotelInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for(HotelPO po : hotelPOList){
            HotelVO hotel = this.hotelPOToVO(po);
            hotelList.add(hotel);
        }

        vo = new HotelListVO(hotelList);

        return vo;
	}
	
	public HotelListVO modifyHotel (HotelVO hotel){
		return null;
	}
	
	public ResultMessage deleteHotel(HotelVO hotel){
		return null;	
	}

	//HotelProxy的私有方法，专门用于将HotelVO转化为HotelPO
	private HotelPO hotelVOToPO(HotelVO hotel){
        int id = hotel.getId();
        String name = hotel.getName();
        String address = hotel.getAddress();
        String contact = hotel.getContact();
        String introduction = hotel.getIntroduction();
        ArrayList<String> facility = hotel.getFacility();
        ArrayList<RoomVO> roomVOList = hotel.getRoomList();

        ArrayList<RoomPO> roomList = new ArrayList<RoomPO>();
        for(RoomVO vo : roomVOList){
            RoomType type = vo.getType();
            double price = vo.getPrice();
            int totalRoomNum = vo.getTotalRoomNum();
            int spareRommNum = vo.getSpareRoomNum();
            RoomPO room = new RoomPO(type, price, totalRoomNum, spareRommNum);
            roomList.add(room);
        }

        int rank = hotel.getRank();
        int score = hotel.getScore();

        ArrayList<CustomerVO> vipVOList = hotel.getVipList();
        ArrayList<CustomerPO> vipList = new ArrayList<CustomerPO>();
        for(CustomerVO vo : vipVOList){
            int customerId = vo.getId();
            String cutomerName = vo.getName();
            String password = vo.getPassword();
            String customerContact = vo.getContact();
            Date birthday = vo.getBirthday();
            double credit = vo.getCredit();
            MemberType type = vo.getType();

            CustomerPO customer = new CustomerPO(id, name , password, contact, birthday, credit, type);
            vipList.add(customer);
        }
        HotelPO po = new HotelPO(id, name, address, contact, introduction, facility, roomList, rank, score, vipList);

        return po;
    }

    //HotelProxy的私有方法，专门用于将HotelPO转化为HotelVO
    private HotelVO hotelPOToVO(HotelPO hotel){
        int id = hotel.getId();
        String name = hotel.getName();
        String address = hotel.getAddress();
        String contact = hotel.getContact();
        String introduction = hotel.getIntroduction();
        ArrayList<String> facility = hotel.getFacility();
        ArrayList<RoomPO> roomPOList = hotel.getRoomList();

        ArrayList<RoomVO> roomList = new ArrayList<RoomVO>();
        for(RoomPO po : roomPOList){
            RoomType type = po.getType();
            double price = po.getPrice();
            int totalRoomNum = po.getTotalRoomNum();
            int spareRommNum = po.getSpareRoomNum();
            RoomVO room = new RoomVO(type, price, totalRoomNum, spareRommNum);
            roomList.add(room);
        }

        int rank = hotel.getRank();
        int score = hotel.getScore();

        ArrayList<CustomerVO> vipList = new ArrayList<CustomerVO>();

        HotelVO vo = new HotelVO(id, name, address, contact, introduction, facility, roomList, rank, score, vipList);
        return vo;
    }
}