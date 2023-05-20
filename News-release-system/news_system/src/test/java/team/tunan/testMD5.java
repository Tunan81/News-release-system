package team.tunan;

import org.apache.commons.codec.digest.DigestUtils;
import team.tunan.entity.GirlFriend;
import team.tunan.entity.News;


/**
 * @author Tunan
 * @version 1.0
 */
public class testMD5 {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("6666"));
    }

    GirlFriend ShenLi = new GirlFriend();
    GirlFriend ShenLi2 = new GirlFriend();
}
