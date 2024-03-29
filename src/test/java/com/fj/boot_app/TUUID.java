package com.fj.boot_app;

import java.util.UUID;


/**
 * java 生成22位UUID 改造
 * 背景：数据库的前期设计，主键用的是uuid，但这个是大数据量的应用。经过n久的折腾，数据大于1亿条了。
 * 返回去看表，发现，表的很多字段是varchar2的，但是长度不超过20字符。占据大部分空间的居然是uuid。于是萌生改造uuid的想法。
 * 过程：经过一番搜索，一般就是缩短至22位的uuid了
 * ---------------------
 * 作者：sskicgah
 * 来源：CSDN
 * 原文：https://blog.csdn.net/sskicgah/article/details/12904881
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 *
 * 64进制：使用64个字符代表
 * 16进制：使用16个字符代表
 * 8进制：使用8个字符代表
 */
public class TUUID {

    /**
     * 采用URL Base64字符，即把“+/”换成“-_”
     */
    static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=".toCharArray();

    /**
     * Base64 编码
     *
     * @param data
     * @return
     */
    private char[] encode(byte[] data) {
        char[] out = new char[((data.length + 2) / 3) * 4];
        boolean quad, trip;
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            quad = trip = false;
            int val = (0xFF & (int) data[i]);
            val <<= 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data[i + 2]);
                quad = true;
            }
            out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 1] = alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = alphabet[val & 0x3F];
        }
        return out;
    }

    /**
     * 转成字节
     *
     * @return
     */
//	private byte[] toBytes(String u) {
//		UUID uuid = UUID.fromString(u);
    private byte[] toBytes() {
        UUID uuid = UUID.randomUUID();
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        byte[] buffer = new byte[16];

        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) ((msb >>> 8 * (7 - i)) & 0xFF);
            buffer[i + 8] = (byte) ((lsb >>> 8 * (7 - i)) & 0xFF);
        }
        return buffer;
    }

    //	public String getUUID(String u) {
//		char[] res = encode(toBytes(u));
    public String getUUID() {
        char[] res = encode(toBytes());
        System.out.println(new String(res));
        return new String(res, 0, res.length - 2);
    }

    public static void main(String[] args) {
        String uuid2 = getUUID22();
        System.out.println(uuid2 + ", " + uuid2.length());

        System.out.println(alphabet.length);

//		System.out.println(getUUID("c19b9de1-f33a-494b-afbe-f06817218d64"));
//		System.out.println(getUUID22("c19b9de1-f33a-494b-afbe-f06817218d64"));
//		Date d1 = new Date();
//		for(int i = 0; i < 1000000; i++) {
//			UUID.randomUUID().toString();
//			getUUID22();
//		}
//		Date d2 = new Date();
//		System.out.print(d2.getTime() - d1.getTime());
    }

    /**
     * 将随机UUID转换成22位字符串
     *
     * @return
     */
//	public static String getUUID22(String u) {
//		UUID uuid = UUID.fromString(u);
    public static String getUUID22() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().length());
//		System.out.println(uuid.toString());
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        char[] out = new char[24];
        int tmp = 0, idx = 0;
        // 基础写法
        /*tmp = (int) ((msb >>> 40) & 0xffffff);
        out[idx + 3] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 2] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 1] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx] = alphabet[tmp & 0x3f];
        idx += 4;

        tmp = (int) ((msb >>> 16) & 0xffffff);
        out[idx + 3] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 2] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 1] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx] = alphabet[tmp & 0x3f];
        idx += 4;

        tmp = (int) (((msb & 0xffff) << 8) | (lsb >>> 56 & 0xff));
        out[idx + 3] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 2] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 1] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx] = alphabet[tmp & 0x3f];
        idx += 4;

        tmp = (int) ((lsb >>> 32) & 0xffffff);
        out[idx + 3] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 2] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 1] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx] = alphabet[tmp & 0x3f];
        idx += 4;

        tmp = (int) ((lsb >>> 8) & 0xffffff);
        out[idx + 3] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 2] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx + 1] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx] = alphabet[tmp & 0x3f];
        idx += 4;

        tmp = (int) (lsb & 0xff);
        out[idx + 3] = alphabet[64];
        out[idx + 2] = alphabet[64];
        tmp <<= 4;
        out[idx + 1] = alphabet[tmp & 0x3f];
        tmp >>= 6;
        out[idx] = alphabet[tmp & 0x3f];
        idx += 4;*/

        // 循环写法
        int bit = 0, bt1 = 8, bt2 = 8;
        int mask = 0x00, offsetm = 0, offsetl = 0;

        for (; bit < 16; bit += 3, idx += 4) {
            offsetm = 64 - (bit + 3) * 8;
            offsetl = 0;
            tmp = 0;

            if (bt1 > 3) {
                mask = (1 << 8 * 3) - 1;
            } else if (bt1 >= 0) {
                mask = (1 << 8 * bt1) - 1;
                bt2 -= 3 - bt1;
            } else {
                mask = (1 << 8 * ((bt2 > 3) ? 3 : bt2)) - 1;
                bt2 -= 3;
            }
            if (bt1 > 0) {
                bt1 -= 3;
                tmp = (int) ((offsetm < 0) ? msb : (msb >>> offsetm) & mask);
                if (bt1 < 0) {
                    tmp <<= Math.abs(offsetm);
                    mask = (1 << 8 * Math.abs(bt1)) - 1;
                }
            }
            if (offsetm < 0) {
                offsetl = 64 + offsetm;
                tmp |= ((offsetl < 0) ? lsb : (lsb >>> offsetl)) & mask;
            }

            if (bit == 15) {
                out[idx + 3] = alphabet[64];
                out[idx + 2] = alphabet[64];
                tmp <<= 4;
            } else {
                out[idx + 3] = alphabet[tmp & 0x3f];
                tmp >>= 6;
                out[idx + 2] = alphabet[tmp & 0x3f];
                tmp >>= 6;
            }
            out[idx + 1] = alphabet[tmp & 0x3f];
            tmp >>= 6;
            out[idx] = alphabet[tmp & 0x3f];
        }

        return new String(out, 0, 22);
    }
}

