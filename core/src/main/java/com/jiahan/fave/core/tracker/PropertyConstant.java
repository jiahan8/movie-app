package com.jiahan.fave.core.tracker;

public class PropertyConstant {
    public static class Name {
        public static final String SCREEN_DISPLAYED       = "Screen Displayed";
        public static final String TAP                    = "Tap";
        public static final String LOCATION_SEARCH_EVENTS = "Location Search Events";
        public static final String FILTER_APPLIED         = "Filter Applied";
    }

    public static class Key {
        //Event Key
        public static final String EVENT_PROPERTY_SCREEN_NAME = "screen_name";
        public static final String EVENT_PROPERTY_TAPPED_ON   = "tapped_on";

        //Attribute Key
        public static final String DEAL_ID             = "deal_id";
        public static final String OUTLET_ID           = "outlet_id";
        public static final String COMPANY_ID          = "company_id";
        public static final String ECARD_ID            = "ecard_id";
        public static final String SELECTED_ECARD_ID   = "selected_ecard_id";
        public static final String POPULAR_LABEL       = "popular_label";
        public static final String POSITION            = "position";
        public static final String TITLE               = "title";
        public static final String CATEGORY            = "category";
        public static final String SELECTED_CATEGORY   = "selected_category";
        public static final String SECTION_NAME        = "section_name";
        public static final String OFFER_NAME          = "offer_name";
        public static final String COMPANY_NAME        = "company_name";
        public static final String ASSORTMENT_NAME     = "collection_name";
        public static final String ASSORTMENT_RIBBON   = "collection_ribbon";
        public static final String ASSORTMENT_TYPE     = "collection_type";
        public static final String PROMO_NAME          = "promo_name";
        public static final String TYPE                = "type";
        public static final String PRODUCT             = "product";
        public static final String APP_FILTER          = "subcategory";
        public static final String LOCATION            = "location";
        public static final String FILTER_APPLIED      = "filter_applied";
        public static final String SORT_APPLIED        = "sort_applied";
        public static final String SELECTED_PRODUCT    = "selected_product";
        public static final String SELECTED_APP_FILTER = "selected_subcategory";
        public static final String FILTER_FAB          = "filter_fab";
        public static final String BANNER              = "banner";
        public static final String KEYWORD             = "keyword";
        public static final String CITY                = "city";
        public static final String FROM_SCREEN_PAGE    = "from_screen_page";
        public static final String SAME_CITY_CLICKED   = "same_city_clicked";
        public static final String LIST_PAGE           = "list_page";
        public static final String SORT_BY             = "sort_by";
        public static final String RATING              = "rating";
        public static final String PRICE_RANGE         = "price_range";
        public static final String DISTANCE            = "distance";
        public static final String REDEEMABLE          = "redeemable";
        public static final String OPENING_HOURS       = "opening_hours";
        public static final String SELECTED_SORTING    = "sort_filter";
    }

    public static class Value {
        public static final String DEAL             = "deal";
        public static final String OUTLET           = "partner";
        public static final String SHOW_ALL_OUTLETS = "show_all_outlets";
        public static final String VIEW_ALL_PHOTOS  = "view_all_photos";
        public static final String COPY_LOCATION    = "copy_location";
        public static final String PAY_NOW          = "pay_now";
        public static final String NONE             = "none";
        public static final String BUY_BUTTON       = "buy_button";
        public static final String GIFT_IT          = "gift_it";
        public static final String LIVE_CHAT        = "live_chat";

        //Location
        public static final String CHANGE_LOCATION         = "change_location";
        public static final String DETECT_CURRENT_LOCATION = "detect_current_location";
        public static final String CHANGE_CITY             = "change_city";
        public static final String RECENT_SEARCHES         = "recent_searches";
        public static final String POPULAR_PLACES          = "popular_places";
        public static final String LOCATION                = "location";
        public static final String FRINGE_CITY             = "fringe_city";
        public static final String SEARCH                  = "search";

        //Deal
        public static final String SHOW_DEAL_DETAIL             = "show_offer_detail";
        public static final String SHARE_THIS_DEAL              = "share_this_deal";
        public static final String DEAL_HEADER                  = "deal_header";
        public static final String ADD_WISHLIST                 = "add_wishlist";
        public static final String TNC_HOW_TO_REDEEM            = "How to redeem";
        public static final String TNC_FINE_PRINT               = "Fine print";
        public static final String TNC_CANCELLATION_POLICY      = "Cancellation policy";
        public static final String VIEW_ALL_REVIEW              = "view_all_reviews";
        public static final String VIEW_ALL_DEAL                = "view_all_offers";
        public static final String DEAL_RATING                  = "offer_rating";
        public static final String EXPAND_SECTION               = "expand_section";
        public static final String OTHER_DEAL_FROM_SAME_PARTNER = "other_offer_from_same_partner";
        public static final String RECOMMENDED_DEALS            = "similar_offer_with_this_offer";

        //Outlet
        public static final String SHOW_OUTLET_DETAIL      = "show_partner_details";
        public static final String SHARE_OUTLET            = "share_partner";
        public static final String OUTLET_HEADER           = "outlet_header";
        public static final String SHOW_ANNOUNCEMENT_PHOTO = "show_announcement_photo";
        public static final String SHOW_MENU_PHOTO         = "show_menu_photo";
        public static final String FAVE_COMPANY            = "fave_partner";
        public static final String SHOW_OPENING_HOURS      = "show_opening_hours";
        public static final String SHOW_MORE_ABOUT         = "about_show_more";
        public static final String GET_CALL_OUTLET         = "call_outlet";
        public static final String GET_DIRECTION           = "get_direction";
        public static final String GET_RIDE                = "get_ride";

        //Assortment
        public static final String VIEW_ALL_ASSORTMENT              = "view_all_collections";
        public static final String SHOW_ASSORTMENT_DETAIL           = "show_offers_by_collection";
        public static final String SHARE_ASSORTMENT                 = "share_collection";
        public static final String ADD_PROMO_CODE                   = "add_promo_code";
        public static final String TNC_ASSORTMENT_DETAIL_PROMO_CODE = "collection_tnc";

        //ECard
        public static final String VIEW_ECARD_DETAIL  = "view_ecard_detail";
        public static final String ECARD_HOW_IT_WORKS = "ecard_how_it_works";
        public static final String CLOSE_HOW_IT_WORKS = "hide_how_it_works";
        public static final String VIEW_ALL_ECARD     = "view_all_ecards";
        public static final String ECARD_ASSORTMENT   = "ecard_collection";
        public static final String ECARD_NEAR_YOU     = "eCards near you";
        public static final String SHARE_THIS_ECARD   = "share_this_ecard";
        public static final String HOW_ECARDS_WORK    = "how_ecards_work";
        public static final String MULTIVALUE_OPTION  = "multivalue_option";

        //Explore
        public static final String MOST_USED      = "most_used";
        public static final String MORE_FOR_YOU   = "more_for_you";
        public static final String EXPLORE_PLACES = "explore_places";

        //Main Category
        public static final String TRENDING_DEALS      = "trending_deals";
        public static final String DYNAMIC_CASHBACK    = "promo_cashback";
        public static final String CATEGORY_ASSORTMENT = "category_collection";
        public static final String SNEAKY_FILTER       = "sneaky_filter";
        public static final String SHOW_BANNER         = "click_banner";
        public static final String VIEW_ALL_OUTLET     = "view_all_outlets";

        //Filter
        public static final String FILTER_RESET = "reset";

        //MyFaves
        public static final String MY_FAVES_EXPLORE = "explore";
        public static final String MY_FAVES_DEALS   = "deals";
        public static final String MY_FAVES_SHOPS   = "shops";
    }
}