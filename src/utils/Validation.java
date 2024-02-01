package utils;

import java.util.ArrayList;
import java.util.Scanner;
import model.BizStudent;
import model.ITStudent;
import model.Student;

public class Validation {
    
    Scanner sc = new Scanner(System.in);
    
    

    public String getValue(String msg) {
        System.out.print(msg);
        return uppercaseFirst(sc.nextLine().toLowerCase());
    }

    public String getAndValidValue(String msg, String regex, String cause) {
        String value;
        while (true) {
            try {
                value = getValue(msg);
                if (value.isEmpty()) {
                    throw new Exception("Input can not empty");
                }
                if (!value.matches(regex)) {
                    throw new Exception(cause);
                }
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return value;
    }

    public double getAndValidScore(String msg) {
        String score;
        while (true) {
            try {
                score = getAndValidValue(msg, "[\\d.]+", "Invalid score");
                if (Double.parseDouble(score) <= 0) {
                    throw new Exception("Score must be a positive number");
                }
                return Double.parseDouble(score);
            } catch (NumberFormatException e) {
                System.out.println("Invalid score");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String trimName(String name) {
        return name.trim();
    }

    public String uppercaseFirst(String string) {
        if (string.isEmpty()) {
            return string;
        } else {
            return Character.toUpperCase(string.charAt(0)) + string.substring(1);
        }
    }

    public String getName(String msg, String regex) {
        while (true) {
            String name = getAndValidValue(msg, regex, "Invalid name");
            return uppercaseFirst(trimName(name));
        }
    }

    public int getInteger(String msg) {
        int value;

        while (true) {
            try {
                System.out.print(msg);
                value = Integer.parseInt(sc.next());

                if (value > 0) {
                    break;
                } else {
                    System.out.println("Please enter a value greater than or equal to 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return value;
    }
    
    public boolean checkDuplicateITID( ArrayList<ITStudent> ITList, String msg){
        int count =0;
        for (ITStudent id : ITList) {
                        if (id.getID().equals(msg)) {
                            count++;
                            break;
                        }
                    }
                    if (count > 0) {
                        return true;
                    }
        return false;
    }
    
    public boolean checkDuplicateBizID( ArrayList<BizStudent> BizList, String msg){
        int count =0;
        for (BizStudent id : BizList) {
                        if (id.getID().equals(msg)) {
                            count++;
                            break;
                        }
                    }
                    if (count > 0) {
                        return true;
                    }
        return false;
    }
}
