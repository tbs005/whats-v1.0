package com.sunder.xie.whats.sso.service;

/**
 * Created by xieshengrong on 2017/5/24.
 */
public interface WhatsLoginService {
    ResultCode<MemberEncodeAuthenticationToken> getLogonMemberByMemberEncodeAndIp(String sessid, String remoteIp);

    ResultCode<MemberEncodeAuthenticationToken> doLogin4MemberEncodeAndSave(String username, String password,
                                                                            String remoteIp);

    void updateMemberAuth(String encodeOrSessid, WheelysMember member);

    void doLogout(String sessid, String ip);
}
