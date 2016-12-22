package group2.grade15.njuse.utility;

/**
 * Created by dell on 2016/12/1.
 */
public enum WebPromotionType {
    TimeWeb,                            //双11活动折扣（在特定的期间预订有折扣）
    AreaWeb,                            //VIP会员特定商圈专属折扣（不同等级不同商圈折扣不一样）
    LevelWeb;                            //制定会员等级（制定信用值满多少升下一等级）及其折扣
    public String toString(){
        switch (this) {
            case TimeWeb:
                return "特定时间优惠";
            case AreaWeb:
                return "特定商圈优惠";
            case LevelWeb:
                return "会员等级优惠";
            default:
                return "";
        }
    }
}
