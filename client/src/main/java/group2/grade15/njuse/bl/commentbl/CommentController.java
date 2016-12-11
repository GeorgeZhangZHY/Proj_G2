package group2.grade15.njuse.bl.commentbl;

import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.blservice.CommentServ;
import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CommentListVO;
import group2.grade15.njuse.vo.CommentVO;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * CommentController的职责是接受评论界面发来的请求
 * 并转交给具体的评论逻辑处理
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class CommentController implements CommentServ, CommentBL {

    OrderBL order;

    public CommentController(){
        order = new Order();
    }

    @Override
    public ResultMessage createComment(CommentVO commentInfo) {
        try {
            return RemoteHelper.getInstance().getCommentDataService().add(commentInfo.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.CONNECTION_EXCEPTION;
        }
    }

    @Override
    public ResultMessage modifyComment(CommentVO modifyInfo) {
        try {
            return RemoteHelper.getInstance().getCommentDataService().modify(modifyInfo.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.CONNECTION_EXCEPTION;
        }
    }

    @Override
    public CommentListVO getHotelCommentList(int hotelId) {
        ArrayList<CommentPO> commentPOList = null;
        ArrayList<CommentVO> commentList = new ArrayList();

        try {
            commentPOList = RemoteHelper.getInstance().getCommentDataService().getHotelComments(hotelId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (commentPOList != null) {
            commentList.addAll(commentPOList.stream()
                                            .map(CommentVO::new)
                                            .collect(Collectors.toList()));
        }

        return new CommentListVO(commentList);
    }

    @Override
    public CommentVO getComment(int orderID) {

        OrderVO orderVO = order.getInfo(orderID);
        int customerID = orderVO.getCustomerID();

        ArrayList<CommentPO> commentPOList = new ArrayList();

        try {
            commentPOList = RemoteHelper.getInstance().getCommentDataService().getCustomerComments(customerID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        CommentVO commentVO = null;

        for (CommentPO comment : commentPOList) {
            if(comment.getOrderID() == orderID){
                commentVO = new CommentVO(comment);
            }
        }

        return commentVO;
    }
}
