
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankerAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean p0 = false;
        boolean p1 = false;
        boolean p2 = false;
        boolean p3 = false;

        //max matrices for the processes
        int p0_max[] = {7, 0, 1, 3};
        int p1_max[] = {2, 7, 5, 0};
        int p2_max[] = {2, 12, 5, 6};
        int p3_max[] = {1, 6, 5, 6};

        //allocation matrices for the processes
        int p0_alo[] = {5, 0, 0, 2};
        int p1_alo[] = {2, 1, 0, 0};
        int p2_alo[] = {0, 6, 3, 3};
        int p3_alo[] = {0, 2, 1, 2};

        //available/work matrix given
        int work_mat[] = {1, 4, 4, 5};

        //total allocation matrix
        int total_alo[] = new int[4];
        for (int i = 0; i < total_alo.length; i++) {
            total_alo[i] = p0_alo[i] + p1_alo[i] + p2_alo[i] + p3_alo[i];
        }

        //number of instances matrix
        int instances[] = new int[4];
        for (int i = 0; i < total_alo.length; i++) {
            instances[i] = work_mat[i] + total_alo[i];
        }

        //need matrices
        //for p0
        int p0_need[] = new int[total_alo.length];
        for (int i = 0; i < p0_need.length; i++) {
            p0_need[i] = p0_max[i] - p0_alo[i];
        }
        //for p1
        int p1_need[] = new int[total_alo.length];
        for (int i = 0; i < p1_need.length; i++) {
            p1_need[i] = p1_max[i] - p1_alo[i];
        }
        //for p2
        int p2_need[] = new int[total_alo.length];
        for (int i = 0; i < p2_need.length; i++) {
            p2_need[i] = p2_max[i] - p2_alo[i];
        }
        //for p3
        int p3_need[] = new int[total_alo.length];
        for (int i = 0; i < p3_need.length; i++) {
            p3_need[i] = p3_max[i] - p3_alo[i];
        }

        //checking now
        String safe_seq[] = new String[work_mat.length];
        int count = 0;
        
        do {
            if (p0 == false && work_mat[0] >= p0_need[0] && work_mat[1] >= p0_need[1] && work_mat[2] >= p0_need[2] && work_mat[3] >= p0_need[3]) {
                for (int i = 0; i < work_mat.length; i++) {
                    work_mat[i] = work_mat[i] + p0_alo[i];
                }
                p0 = true;
                safe_seq[count] = "Process p0";
                count++;
                
            }
             if (p1 == false && work_mat[0] >= p1_need[0] && work_mat[1] >= p1_need[1] && work_mat[2] >= p1_need[2] && work_mat[3] >= p1_need[3]) {
                for (int i = 0; i < work_mat.length; i++) {
                    work_mat[i] = work_mat[i] + p1_alo[i];
                }
                p1 = true;
                safe_seq[count] = "Process p1";
                count++;
                
            }
             if (p2 == false && work_mat[0] >= p2_need[0] && work_mat[1] >= p2_need[1] && work_mat[2] >= p2_need[2] && work_mat[3] >= p2_need[3]) {
                for (int i = 0; i < work_mat.length; i++) {
                    work_mat[i] = work_mat[i] + p2_alo[i];
                }
                p2 = true;
                safe_seq[count] = "Process p2";
                count++;
                
            }
             if (p3 == false && work_mat[0] >= p3_need[0] && work_mat[1] >= p3_need[1] && work_mat[2] >= p3_need[2] && work_mat[3] >= p3_need[3]) {
                for (int i = 0; i < work_mat.length; i++) {
                    work_mat[i] = work_mat[i] + p3_alo[i];
                }
                p3 = true;
                safe_seq[count] = "Process p3";
                count++;
               
            }
        } while (count < 4);
        System.out.println("The safe sequence is : \n");
        for (int i = 0; i < safe_seq.length; i++) {
            System.out.println(safe_seq[i]);
        }

    }

}
