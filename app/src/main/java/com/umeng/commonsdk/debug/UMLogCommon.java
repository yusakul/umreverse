package com.umeng.commonsdk.debug;

public class UMLogCommon
{
    public static final String SC_10001;
    public static final String SC_10002;
    public static final String SC_10003 = "\u7edf\u8ba1SDK\u521d\u59cb\u5316\u6210\u529f";
    public static final String SC_10004 = "PUSH AppKey\u8bbe\u7f6e\u6210\u529f";
    public static final String SC_10005 = "PUSH Channel\u8bbe\u7f6e\u6210\u529f";
    public static final String SC_10006 = "Share AppKey\u8bbe\u7f6e\u6210\u529f";
    public static final String SC_10007;
    public static final String SC_10008 = "AppKey\u6539\u53d8!!!";
    public static final String SC_10009 = "PUSH Secret\u8bbe\u7f6e\u6210\u529f";
    public static final String SC_10010 = "\u9519\u8bef\u5206\u6790SDK\u521d\u59cb\u5316\u6210\u529f";
    public static final String SC_10011 = "\u8bf7\u6ce8\u610f\uff1a\u60a8init\u63a5\u53e3\u4e2d\u8bbe\u7f6e\u7684AppKey\u662f@\uff0cmanifest\u4e2d\u8bbe\u7f6e\u7684AppKey\u662f#\uff0cinit\u63a5\u53e3\u8bbe\u7f6e\u7684AppKey\u4f1a\u8986\u76d6manifest\u4e2d\u8bbe\u7f6e\u7684AppKey";
    
    static {
        SC_10001 = "\u4e0d\u80fd\u5728\u975e\u4e3b\u8fdb\u7a0b\u8fdb\u884c\u521d\u59cb\u5316|\u76ee\u524d\u53ea\u80fd\u5728\u4e3b\u8fdb\u7a0b\u8fdb\u884c\u521d\u59cb\u5316\uff0c\u5982\u4f55\u6b63\u786e\u521d\u59cb\u5316\u8bf7\u8be6\u89c1\u5730\u5740\uff1a" + UMLogUtils.makeUrl("67292");
        SC_10002 = "\u4e0d\u80fd\u5728\u975eApplication\u7684onCreate\u65b9\u6cd5\u4e2d\u8fdb\u884c\u521d\u59cb\u5316|\u76ee\u524d\u53ea\u80fd\u5728Application\u7684onCreate\u65b9\u6cd5\u4e2d\u8fdb\u884c\u521d\u59cb\u5316\uff0c\u5982\u4f55\u6b63\u786e\u521d\u59cb\u5316\u8bf7\u8be6\u89c1\u5730\u5740\uff1a" + UMLogUtils.makeUrl("67292");
        SC_10007 = "AppKey\u4e0d\u80fd\u4e3a\u7a7a|\u60a8\u5fc5\u987b\u6b63\u786e\u8bbe\u7f6eAppKey\uff0c\u5982\u4f55\u6b63\u786e\u521d\u59cb\u5316\u8bf7\u8be6\u89c1\u5730\u5740\uff1a" + UMLogUtils.makeUrl("67292");
    }
}
