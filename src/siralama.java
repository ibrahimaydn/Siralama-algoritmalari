
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class siralama extends JFrame {

    public static int dizi[] = new int[5];

    private int[] array;  // sıralanacak dizi
    // GUI kısmı

    JButton button = new JButton("Generate Numbers");
    private JTextField boyut;
    private JTextField giriş;
    private JButton siralamabuton;
    private JButton randombuton;
    private JButton çizdirbuton;
    private JComboBox<String> comboBox;
    private JLabel çıkışlabel;
    private JLabel adımlabel;
    public JLabel diziboyut;

    //guı kurum
    public siralama() {

        setTitle("sıralama GUI");
        setSize(400,300);
        setLayout(new FlowLayout());

        diziboyut = new JLabel();
        add(diziboyut);
        diziboyut.setText("Dizi boyutu=");

        boyut = new JTextField(10);
        add(boyut);

        randombuton = new JButton("Random");
        add(randombuton);

        giriş = new JTextField(30);
        add(giriş);

        siralamabuton = new JButton("Sırala");
        add(siralamabuton);

        comboBox = new JComboBox<>(new String[]{"Bubble Sort", "Quick Sort" , "Selection Sort","Insertion Sort","Merge Sort"});
        add(comboBox);

        çıkışlabel = new JLabel();
        add(çıkışlabel);

        adımlabel = new JLabel();
        add(adımlabel);
        çizdirbuton = new JButton("Çizdir");
        add(çizdirbuton);

        //Random sayı üretme
        randombuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(boyut.getText());
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    int random = (int) (Math.random() * 1000);
                    sb.append(random).append(",");
                    giriş.setText(sb.toString());
                }
            }
        });

        //çizdirme 
        çizdirbuton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < dizi.length; i++) {
                    if(i==0) System.out.println(dizi[0]+"=bubble sıralama");
                    if(i==1) System.out.println(dizi[1]+"=quick sıralama");
                    if(i==2) System.out.println(dizi[2]+"=selection sıralama");
                    if(i==3) System.out.println(dizi[3]+"=insertion sıralama");
                    if(i==4) System.out.println(dizi[4]+"=merge sıralama");

                }
            }
        });

        // Sıralama düğmesi 
        siralamabuton.addActionListener(new ActionListener() {

                                            public void actionPerformed(ActionEvent e) {

                                                // Giriş dizesini bir tamsayı dizisine ayrıştırın
                                                String input = giriş.getText();
                                                String[] parca = input.split(",");
                                                array = new int[parca.length];
                                                for (int i = 0; i < parca.length; i++) {
                                                    array[i] = Integer.parseInt(parca[i]);
                                                }

                                                // seçilen combo kısmı
                                                String sortMethod = (String) comboBox.getSelectedItem();

                                                // Diziyi seçilen algoritmayı kullanarak sırala
                                                switch (sortMethod) {
                                                    case "Bubble Sort":
                                                        bubbleSort(array);
                                                        System.out.println("baloncuk sıralama");
                                                        break;
                                                    case "Quick Sort":
                                                        quickSortI(array);
                                                        System.out.println("hızlı sıralama");
                                                        break;
                                                    case "Selection Sort":
                                                        selectionSort(array);
                                                        System.out.println("seçme sıralama");
                                                        break;
                                                    case "Insertion Sort":
                                                        insertionSort(array);
                                                        System.out.println("ekleme sıralama");
                                                        break;
                                                    case "Merge Sort":
                                                        mergeSort(array);
                                                        System.out.println("birleşme sıralaması");
                                                        break;
                                                } // sıralı diziyi gösterme
                                                çıkışlabel.setText(arrayToString(array));
                                                System.out.println(arrayToString(array));
                                            }
                                        }
        );
    }

    // kabarcık sıralama 
    public static void bubbleSort(int[] array) {
        int a = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    a++;
                }

            }
        }
        dizi[0] += a;
        System.out.println("");
        System.out.println(a+" adımda sıraladı");

    }
//quick sıralama

    public static void quickSortI(int[] array) {
        int a = 0;
        // Sıralama yapılacak dizinin boyutunu tut
        int n = array.length;

        // Sıralama yapılacak dizinin sol ve sağ taraflarını tut
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();

        // Sol ve sağ tarafları stack'e ekle
        leftStack.push(0);
        rightStack.push(n - 1);

        // Stack boş değilken
        while (!leftStack.isEmpty()) {

            // Sol ve sağ tarafları stack'ten çıkar
            int left = leftStack.pop();
            int right = rightStack.pop();

            // Pivot'u seç
            int pivotIndex = (left + right) / 2;
            int pivot = array[pivotIndex];

            // Dizinin elemanlarını pivot'a göre ikiye ayır
            int i = left;
            int j = right;
            while (i < j) {

                // i'yi pivot'tan büyük bir elemana kadar ilerlet
                while (array[i] < pivot) {

                    i++;
                }
                // j'yi pivot'tan küçük bir elemana kadar gerilet
                while (array[j] > pivot) {

                    j--;
                }
                // i ve j yine de birbirinden farklıysa, yerlerini değiştir
                if (i < j) {
                    a++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i++;
                    j--;
                }
            }

            // Sol tarafın boyutu 2 veya daha fazlaysa, sol tarafı stack'e ekle
            if (j - left > 1) {

                leftStack.push(left);
                rightStack.push(j);
            }
            // Sağ tarafın boyutu 2 veya daha fazlaysa, sağ tarafı stack'e ekle
            if (right - i > 1) {

                leftStack.push(i);
                rightStack.push(right);
            }
        }
        dizi[1] += a;
        System.out.println("");
        System.out.println(a+" adımda sıraladı");
    }

    // seçme sıralama 
    public static void selectionSort(int[] array) {
        int a = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;a++;
                }

            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        dizi[2] += a;
        System.out.println("");
        System.out.println(a+" adımda sıraladı");
    }

    // ekleme sıralaması 
    public static void insertionSort(int[] array) {
        int a = 0;
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                a++;
            }
            array[j + 1] = key;
        }
        dizi[3] += a;
        System.out.println("");
        System.out.println(a+" adımda sıraladı");
    }

    // birleşme sıralaması 
    public static void mergeSort(int[] array) {
        int a = 0;
        if (array.length <= 1) {
            return;
        }

        // Diziyi ikiye böl
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        for (int i = 0; i < mid; i++) {

            left[i] = array[i];
        }
        for (int i = 0; i < array.length - mid; i++) {
            right[i] = array[mid + i];

        }

        // Sol ve sağ tarafları recursive olarak sırala
        mergeSort(left);
        mergeSort(right);

        // Sol ve sağ tarafları birleştir
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {

            if (left[i] < right[j]) {
                a++;
                array[k] = left[i];
                i++;
            } else {

                array[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {

            array[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {

            array[k] = right[j];
            j++;
            k++;
        }

        dizi[4] += a;
        System.out.println("");
        System.out.print(a+" adımda sıraladı");
    }

    // bir diziyi başka bir diziye dönüştürme
    public static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        siralama gui = new siralama();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.getContentPane().setBackground(Color.LIGHT_GRAY);
    }
}
