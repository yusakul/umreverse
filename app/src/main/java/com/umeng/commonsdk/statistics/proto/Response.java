package com.umeng.commonsdk.statistics.proto;

import java.io.*;
import com.umeng.commonsdk.proguard.*;
import java.util.*;

public class Response implements j, Serializable, Cloneable
{
    private static final long serialVersionUID = -4549277923241195391L;
    private static final an STRUCT_DESC;
    private static final ad RESP_CODE_FIELD_DESC;
    private static final ad MSG_FIELD_DESC;
    private static final ad IMPRINT_FIELD_DESC;
    private static final Map<Class<? extends aq>, ar> schemes;
    public int resp_code;
    public String msg;
    public com.umeng.commonsdk.statistics.proto.d imprint;
    private static final int __RESP_CODE_ISSET_ID = 0;
    private byte __isset_bitfield;
    private e_enum[] optionals;
    public static final Map<e_enum, v> metaDataMap;
    
    public Response() {
        this.__isset_bitfield = 0;
        this.optionals = new e_enum[] { e_enum.b, e_enum.c };
    }
    
    public Response(final int resp_code) {
        this();
        this.resp_code = resp_code;
        this.setResp_codeIsSet(true);
    }
    
    public Response(final Response response) {
        this.__isset_bitfield = 0;
        this.optionals = new e_enum[] { e_enum.b, e_enum.c };
        this.__isset_bitfield = response.__isset_bitfield;
        this.resp_code = response.resp_code;
        if (response.isSetMsg()) {
            this.msg = response.msg;
        }
        if (response.isSetImprint()) {
            this.imprint = new com.umeng.commonsdk.statistics.proto.d(response.imprint);
        }
    }
    
    @Override
    public Response deepCopy() {
        return new Response(this);
    }
    
    @Override
    public void clear() {
        this.setResp_codeIsSet(false);
        this.resp_code = 0;
        this.msg = null;
        this.imprint = null;
    }
    
    public int getResp_code() {
        return this.resp_code;
    }
    
    public Response setResp_code(final int resp_code) {
        this.resp_code = resp_code;
        this.setResp_codeIsSet(true);
        return this;
    }
    
    public void unsetResp_code() {
        this.__isset_bitfield = g.b(this.__isset_bitfield, 0);
    }
    
    public boolean isSetResp_code() {
        return g.a(this.__isset_bitfield, 0);
    }
    
    public void setResp_codeIsSet(final boolean b) {
        this.__isset_bitfield = g.a(this.__isset_bitfield, 0, b);
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public Response setMsg(final String msg) {
        this.msg = msg;
        return this;
    }
    
    public void unsetMsg() {
        this.msg = null;
    }
    
    public boolean isSetMsg() {
        return this.msg != null;
    }
    
    public void setMsgIsSet(final boolean b) {
        if (!b) {
            this.msg = null;
        }
    }
    
    public com.umeng.commonsdk.statistics.proto.d getImprint() {
        return this.imprint;
    }
    
    public Response setImprint(final com.umeng.commonsdk.statistics.proto.d imprint) {
        this.imprint = imprint;
        return this;
    }
    
    public void unsetImprint() {
        this.imprint = null;
    }
    
    public boolean isSetImprint() {
        return this.imprint != null;
    }
    
    public void setImprintIsSet(final boolean b) {
        if (!b) {
            this.imprint = null;
        }
    }
    


    public q fieldForId(int arg1) {
        return com.umeng.commonsdk.statistics.proto.Response.e_enum.a(arg1);
    }


    
    @Override
    public void read(final ai ai) throws p {
        Response.schemes.get(ai.D()).b().b(ai, this);
    }
    
    @Override
    public void write(final ai ai) throws p {
        Response.schemes.get(ai.D()).b().a(ai, this);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.resp_code);
        int n = 0;
        if (this.isSetMsg()) {
            if (n == 0) {
                sb.append(", ");
            }
            sb.append("msg:");
            if (this.msg == null) {
                sb.append("null");
            }
            else {
                sb.append(this.msg);
            }
            n = 0;
        }
        if (this.isSetImprint()) {
            if (n == 0) {
                sb.append(", ");
            }
            sb.append("imprint:");
            if (this.imprint == null) {
                sb.append("null");
            }
            else {
                sb.append(this.imprint);
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    public void validate() throws p {
        if (this.imprint != null) {
            this.imprint.l();
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        try {
            this.write(new ac(new au(objectOutputStream)));
        }
        catch (p p) {
            throw new IOException(p.getMessage());
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.__isset_bitfield = 0;
            this.read(new ac(new au(objectInputStream)));
        }
        catch (p p) {
            throw new IOException(p.getMessage());
        }
    }
    
    static {
        STRUCT_DESC = new an("Response");
        RESP_CODE_FIELD_DESC = new ad("resp_code", (byte)8, (short)1);
        MSG_FIELD_DESC = new ad("msg", (byte)11, (short)2);
        IMPRINT_FIELD_DESC = new ad("imprint", (byte)12, (short)3);
        (schemes = new HashMap<Class<? extends aq>, ar>()).put(as.class, new InnerClass_b());
        Response.schemes.put(at.class, new InnerClass_d());
        final EnumMap<e_enum, v> m = new EnumMap<e_enum, v>(e_enum.class);
        m.put(e_enum.a, new v("resp_code", (byte)1, new w((byte)8)));
        m.put(e_enum.b, new v("msg", (byte)2, new w((byte)11)));
        m.put(e_enum.c, new v("imprint", (byte)2, new aa((byte)12, com.umeng.commonsdk.statistics.proto.d.class)));
        v.a(Response.class, metaDataMap = Collections.unmodifiableMap((Map)m));
    }
    
    public enum e_enum implements q
    {
        a((short)1, "resp_code"), 
        b((short)2, "msg"), 
        c((short)3, "imprint");
        
        private static final Map<String, e_enum> d;
        private final short e;
        private final String f;
        
        public static e_enum a(final int n) {
            switch (n) {
                case 1: {
                    return e_enum.a;
                }
                case 2: {
                    return e_enum.b;
                }
                case 3: {
                    return e_enum.c;
                }
                default: {
                    return null;
                }
            }
        }
        
        public static e_enum b(final int i) {
            final e_enum a = a(i);
            if (a == null) {
                throw new IllegalArgumentException("Field " + i + " doesn't exist!");
            }
            return a;
        }
        
        public static e_enum a(final String s) {
            return e_enum.d.get(s);
        }
        
        private e_enum(final short e, final String f) {
            this.e = e;
            this.f = f;
        }
        
        @Override
        public short a() {
            return this.e;
        }
        
        @Override
        public String b() {
            return this.f;
        }
        
        static {
            d = new HashMap<String, e_enum>();
            for (final e_enum e : EnumSet.allOf(e_enum.class)) {
                e.d.put(e.b(), e);
            }
        }
    }
    
    private static class InnerClass_b implements ar
    {
        public InnerClass_a a() {
            return new InnerClass_a();
        }

        public aq b() {
            return this.a();
        }
    }
    
    private static class InnerClass_a extends as
    {

        public void a(ai arg1, j arg2) throws p {
            this.b(arg1, ((Response)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.a(arg1, ((Response)arg2));
        }

        public void a(final ai ai, final Response response) throws p {
            ai.j();
            while (true) {
                final ad l = ai.l();
                if (l.b == 0) {
                    break;
                }
                switch (l.c) {
                    case 1: {
                        if (l.b == 8) {
                            response.resp_code = ai.w();
                            response.setResp_codeIsSet(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 2: {
                        if (l.b == 11) {
                            response.msg = ai.z();
                            response.setMsgIsSet(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    case 3: {
                        if (l.b == 12) {
                            (response.imprint = new com.umeng.commonsdk.statistics.proto.d()).read(ai);
                            response.setImprintIsSet(true);
                            break;
                        }
                        al.a(ai, l.b);
                        break;
                    }
                    default: {
                        al.a(ai, l.b);
                        break;
                    }
                }
                ai.m();
            }
            ai.k();
            if (!response.isSetResp_code()) {
                throw new aj("Required field 'resp_code' was not found in serialized data! Struct: " + this.toString());
            }
            response.validate();
        }
        

        public void b(final ai ai, final Response response) throws p {
            response.validate();
            ai.a(Response.STRUCT_DESC);
            ai.a(Response.RESP_CODE_FIELD_DESC);
            ai.a(response.resp_code);
            ai.c();
            if (response.msg != null && response.isSetMsg()) {
                ai.a(Response.MSG_FIELD_DESC);
                ai.a(response.msg);
                ai.c();
            }
            if (response.imprint != null && response.isSetImprint()) {
                ai.a(Response.IMPRINT_FIELD_DESC);
                response.imprint.write(ai);
                ai.c();
            }
            ai.d();
            ai.b();
        }
    }
    
    private static class InnerClass_d implements ar
    {
        public InnerClass_c a() {
            return new InnerClass_c();
        }

        public aq b() {
            return this.a();
        }
    }
    
    private static class InnerClass_c extends at
    {

        public void a(ai arg1, j arg2) throws p {
            this.a(arg1, ((Response)arg2));
        }

        public void b(ai arg1, j arg2) throws p {
            this.b(arg1, ((Response)arg2));
        }

        public void a(final ai ai, final Response response) throws p {
            final ao ao = (ao)ai;
            ao.a(response.resp_code);
            final BitSet set = new BitSet();
            if (response.isSetMsg()) {
                set.set(0);
            }
            if (response.isSetImprint()) {
                set.set(1);
            }
            ao.a(set, 2);
            if (response.isSetMsg()) {
                ao.a(response.msg);
            }
            if (response.isSetImprint()) {
                response.imprint.write(ao);
            }
        }

        public void b(final ai ai, final Response response) throws p {
            final ao ao = (ao)ai;
            response.resp_code = ao.w();
            response.setResp_codeIsSet(true);
            final BitSet b = ao.b(2);
            if (b.get(0)) {
                response.msg = ao.z();
                response.setMsgIsSet(true);
            }
            if (b.get(1)) {
                (response.imprint = new com.umeng.commonsdk.statistics.proto.d()).read(ao);
                response.setImprintIsSet(true);
            }
        }
    }
}
