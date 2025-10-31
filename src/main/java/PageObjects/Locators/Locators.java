package PageObjects.Locators;


public class Locators {

    public static class LoginPage{

        public static String EmailByID = "email";
        public static String PasswordByID = "password";
        public static String SignInByID = "next";

    }

    public static class DashboardPage{

        public static String SkipButtonByXPATH = "//div[@class='contact-box maintextdiv skipBtn']";
        public static String ContactUsButtonByXPATH = "//div[@class='contact-box maintextdiv contactBtn']";

    }

    public static class AdministratorPage{
        public static String menuLinkXpath(String linkText) {
            return String.format("//span[normalize-space(text())='%s']", linkText);
        }
    }

    public static class ScenariosPage{
        public static String CreateNewScenarioButtonByID =  "createnewscenario";
    }

}
