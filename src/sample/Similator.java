package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class Similator {

    public Float[][] Similator (List<File> files){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Float [][] correlation = new Float[files.size()][files.size()];
        for (int o = 0; o < files.size(); o++) {
            for (int q = 0; q < files.size(); q++) {
                File code1 = files.get(o);
                File code2 = files.get(q);
                String[] first = new String[1000];
                String[] second = new String[1000];
                int i = 0;
                int j = 0;

                try (BufferedReader br = new BufferedReader(new FileReader(code1))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        first[i] = line;
                        i++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (BufferedReader br = new BufferedReader(new FileReader(code2))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        second[j] = line;
                        j++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                float x = 0;
                for (int z = 0; z < i; z++) {
                    for (int n = 0; n < j; n++) {
                        if (first[z].equals(second[n])) {
                            x++;
                            break;
                        }
                    }
                }

                float perLine = (x / i);
                perLine = Float.valueOf(decimalFormat.format(perLine));
                correlation[o][q] = perLine;
        }
        }
        return correlation;
    }
}
