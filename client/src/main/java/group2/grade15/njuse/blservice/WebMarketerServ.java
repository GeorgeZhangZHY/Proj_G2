package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

public interface WebMarketerServ {
	public WebMarketerVO getInfo(String webMarketerId) ;
	
	public WebPromotionVO createWebPromotion (WebPromotionVO promotionInfo);
	
	public WebPromotionListVO getWebPromotionList (String hotelId);
	
	public ResultMessage modifyWebPromotion (WebPromotionVO promotion);
	
	public ResultMessage changeState(WebPromotionVO PromotionVO);
	
	public OrderListVO getAbnomalOrderList(int customerId);
	
	public ResultMessage modifyCredit(CreditVO Credit);

}

