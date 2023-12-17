package Extensions;

import org.apache.commons.lang3.RandomStringUtils;

public class Extensions {
    public static String generateRandomString(String input, int length) {
        String randomString = RandomStringUtils.randomAlphabetic(length);
        return input + randomString;
    }
}
