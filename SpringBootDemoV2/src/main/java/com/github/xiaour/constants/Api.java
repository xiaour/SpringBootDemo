package com.github.xiaour.constants;

/**
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/3/13 上午10:09
 */
public class Api {

    /**
     * 获取第三方平台component_access_token
     */
    public  static final String component_token="https://api.weixin.qq.com/cgi-bin/component/api_component_token";

    /**
     * 获取预授权码pre_auth_code
     */
    public  static final String create_preauthcode="https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=";

    /**
     * 使用授权码换取公众号或小程序的接口调用凭据和授权信息
     */
    public  static final String query_auth="https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=";

    /**
     * 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）
     */
    public  static final String authorizer_token="https:// api.weixin.qq.com /cgi-bin/component/api_authorizer_token?component_access_token=";

    /**
     * 获取授权方的帐号基本信息
     */
    public  static final String get_authorizer_info="https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=";

    /**
     * 获取授权方的选项设置信息
     */
    public  static final String get_authorizer_option="https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_option?component_access_token=";

    /**
     * 设置授权方的选项信息
     */
    public  static final String set_authorizer_option="https://api.weixin.qq.com/cgi-bin/component/api_set_authorizer_option?component_access_token=";


    enum Type{
        A;
    }


}
