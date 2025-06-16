import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите операцию\n 1.Сохранить\n 2.Восстановить\n 3.Выход");
        while(true){
            int input = in.nextInt();
            if (input == 1) {
                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saved.txt"))){
                    System.out.println("Введите x");
                    double x = in.nextDouble();
                    Solution s = new Solution(x);
                    System.out.println("Значение y = " + s.y);
                    oos.writeObject(s);
                    System.out.println("Файл сохранён");
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            }
            else if (input == 2) {
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saved.txt"))) {
                    Solution s = (Solution) ois.readObject();
                    System.out.printf("Значение x = %f\ty = %f\n",s.x,s.y);
                } catch (ClassNotFoundException ex){
                    throw new RuntimeException(ex);
                } catch (Exception e){
                    e.printStackTrace(System.out);
                }
            }
            else break;
            System.out.println("Выберите операцию\n 1.Сохранить\n 2.Восстановить\n 3.Выход");
        }
    }
}
class Solution implements Serializable{
    double x;
    double y;
    void exp(){
        this.y = x - Math.sin(x);
    }
    Solution(double x){
        this.x = x;
        exp();
    }
}