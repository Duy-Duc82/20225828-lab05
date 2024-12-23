package hust.soict.dsai.test.disc;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class TestPassingParameter {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");
        
        swap(jungleDVD, cinderellaDVD);
        System.out.print("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("\n");
        System.out.print("cinderella dvd title: " + cinderellaDVD.getTitle());
        System.out.println("\n");
        changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.print("jungle dvd title: " + jungleDVD.getTitle());
    }
    
    public static void swap(Object o1, Object o2) {
        Object tmp = o1;
        o1 = o2;
        o2 = tmp;
    }
    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTitle);
    }
}

class DigitalVideoDisc {
    private String title;
    
    public DigitalVideoDisc(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
// sau khi hoàn thành bài 4 output jungle dvd title : ABC