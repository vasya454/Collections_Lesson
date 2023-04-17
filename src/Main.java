import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Elephant> herd = new ArrayList<>();
        herd.add(new Elephant("Рама", 290));
        herd.add(new Elephant("Дима", 200));
        herd.add(new Elephant("Маша", 199));
        LinkedList<Elephant> elephantList = new LinkedList<>();
        elephantList.addAll(herd);
        System.out.println(elephantList);
        elephantList.add(herd.get(1));
        herd.add(new Elephant("Хари", 256));
        elephantList.add(herd.get(3));
        elephantList.add(herd.get(3));
        System.out.println(elephantList);
        elephantList.sort(new Comparator<Elephant>() {
            @Override
            public int compare(Elephant o1, Elephant o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        System.out.println(distinctCount(elephantList));
        System.out.println(countDistinctS(elephantList));
        Set<Elephant> distinctElephants = new HashSet<>();
        distinctElephants.addAll(elephantList);
        System.out.println(distinctElephants);

        elephantList.sort((o1, o2) -> o1.name.compareTo(o2.name));
        elephantList.sort(Comparator.comparing(Elephant::getName));

        System.out.println(elephantList.stream().distinct().count());
    }

    private static <T>int countDistinctS(List<T> tList) {
        //Узнать, сколько разных слонов в списке?
        int counter=0;
        if (tList.isEmpty())
            return 0;
        tList.sort(Comparator.comparing(T::toString));// O(N) = N*log(N)
        for (int i = 1; i < tList.size(); i++) {             // O(N) = N
            if(!tList.get(i).equals(tList.get(i-1)))
                counter++;
        }
        return counter+1;
        // O(N) = N* (1+ log(N))
    }

    static int distinctCount(LinkedList<Elephant> elephantList) {
        int i = 0, counter = 0;
        for (Elephant e1 : elephantList) {
            for (int j = ++i; j < elephantList.size(); j++)
                if (e1.equals(elephantList.get(j)))
                    counter++;
        }
        return elephantList.size()-counter;
    }
}

class Elephant {
    String name;
    double weight;

    public Elephant(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Elephant{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}