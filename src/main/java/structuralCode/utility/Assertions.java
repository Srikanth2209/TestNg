package structuralCode.utility;

public class Assertions
{
    ActionReUsable actionReUsable = new ActionReUsable();

    /**
     * This method id to check Whether expected title matches with actual or not
     *       Returns: true,if matched
     *       false, if mismatched
     * @param exp_Title
     * @return
     */
    public boolean verifyPageTitle(String exp_Title) {
        String actual_Title = actionReUsable.title();

        if (actual_Title.equals(exp_Title)) {

            System.out.println("Expected title and actual title  Matched");
            return true;
        }else {
            System.out.println("Expected title and actual title Mismatched");
            return false;
        }
    }
}
