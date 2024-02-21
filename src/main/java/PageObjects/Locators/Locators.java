package PageObjects.Locators;


public class Locators {

    public static class LoginPage{

        public static String UserNameID = "txtUser";
        public static String PasswordID = "txtPassword";
        public static String LoginButtonID = "btnLogin";

    }

    public static class HomePage{

        public static String LoggedUserNameXpath = "//a[@class='dropdown-toggle']";

    }
}
