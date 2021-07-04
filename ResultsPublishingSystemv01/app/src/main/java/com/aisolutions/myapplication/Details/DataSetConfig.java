package com.aisolutions.myapplication.Details;

public class DataSetConfig {

    //Final Details
    public static String index = "";
    public static String password = "";
    public static String name = "";
    public static String gpa = "";
    public static String rank = "";

    //Details Array
    //Json data fill into this array
    public static String[] Detail_semester_1 = new String[14];
    public static String[] Detail_semester_2 = new String[13];
    public static String[] Detail_semester_3 = new String[14];
    public static String[] Detail_semester_4 = new String[13];
    public static String[] Detail_semester_5 = new String[14];
    public static String[] Detail_semester_6 = new String[12];

    public static String[] Detail_sem_gpa = new String[6];

    //Result Status Array
    public static String[] Status_semester_1 = new String[10];
    public static String[] Status_semester_2 = new String[9];
    public static String[] Status_semester_3 = new String[10];
    public static String[] Status_semester_4 = new String[9];
    public static String[] Status_semester_5 = new String[10];
    public static String[] Status_semester_6 = new String[8];


    //Subject Codes Array
    public static String[] Code_semester_1 = {"CO1121", "CO1122", "CO1112", "CO1123", "CO1124", "CO1114", "CO1125", "CO1115", "CO1126", "GEP-I"};
    public static String[] Code_semester_2 = {"CO1221", "CO1222", "CO1212", "CO1223", "CO1213", "CO1224", "CO1214", "CO1225", "CO1226"};
    public static String[] Code_semester_3 = {"CO2121", "CO2122", "CO2112", "CO2123", "CO2124", "CO2114", "CO2125", "CO2115", "CO2126", "GEP-III"};
    public static String[] Code_semester_4 = {"CO2221", "CO2222", "CO2212", "CO2223", "CO2213", "CO2224", "CO2214", "CO2225", "CO2226"};
    public static String[] Code_semester_5 = {"CS3121", "CS3111", "CS3122", "CS3112", "CS3123", "CS3113", "CS3124", "CS3114", "CS3135", "EC3101"};
    public static String[] Code_semester_6 = {"CS3221", "CS3211", "CS3224", "CS3214", "CS3233", "CS3222", "CS3212", "CS3235"};

    //Subject Names Array
    public static String[] SubjectName_semester_1 = {"Basic Mathematics for Computing", "Basic Computer Programming", "Practical Work on CO1122", "Formal Methods for Problem Solving", "Computer Systems & PC Applications", "Practical Work on CO1124", "Statistics for Science & Technology", "Practical Work on CO1125", "Management Information System", "General English Proficiency -I"};
    public static String[] SubjectName_semester_2 = {"System Analysis Design", "Data Structure and Algorithm", "Practical Work on CO1222", "Database Management Systems", "Practical Work on CO1223", "Multimedia & Hypermedia Development", "Practical Work on CO1224", "Computer Architecture", "Social Harmony"};
    public static String[] SubjectName_semester_3 = {"Advance Mathematics for Computing", "Operating System", "Practical Work on CO2122", "Software Engineering", "Internet & Web Design", "Practical Work on CO2124", "Object Oriented Programming", "Practical Work on CO2125", "Sri Lankan Studies", "General English Proficiency -III"};
    public static String[] SubjectName_semester_4 = {"Data Communication Systems", "Visual System Development Tools", "Practical work on CO2222", "Computer Graphics", "Practical work on CO2223", "Human Computer Interaction", "Practical work on CO2224", "Software Management Tools", "Automata Theory"};
    public static String[] SubjectName_semester_5 = {"Logic Programming and Expert Systems", "Practical Work on CS3121", "Advanced Database Management Systems", "Practical Work on CS3122", "Systems & Network Administration", "Practical Work on CS3123", "Data Security", "Practical Work on CS3124", "Theory of Computing", "Foundations of Management"};
    public static String[] SubjectName_semester_6 = {"Assembly Language Programming", "Practical work on CS3221", "Computer Network", "Practical work on CS3224", "Professional Issues in IT", "Software Quality Assurance", "Practical work on CS 3222", "Industrial Project"};

    //Subject GPA Values Array
    public static String[] GpaValue_semester_1 = {"2", "2", "1", "2", "2", "1", "2", "1", "2", "N/A"};
    public static String[] GpaValue_semester_2 = {"2", "2", "1", "2", "1", "2", "1", "2", "2"};
    public static String[] GpaValue_semester_3 = {"2", "2", "1", "2", "2", "1", "2", "1", "2", "N/A"};
    public static String[] GpaValue_semester_4 = {"2", "2", "1", "2", "1", "2", "1", "2", "2"};
    public static String[] GpaValue_semester_5 = {"2", "2", "1", "2", "2", "1", "2", "1", "3", "N/A"};
    public static String[] GpaValue_semester_6 = {"2", "1", "2", "1", "3", "2", "1", "3"};

    //---------------------Fill marks into Array--------------------------------
    public static String[] dataIntoArray(String textString, int lengthInt) {
        String[] temp = textString.split(",");
        String[] array = new String[lengthInt];
        int pass = 0;
        int repeat = 0;
        int not_attend = 0;
        int less_attend = 0;

        for (int i = 0; i < temp.length; i++) {
            switch (temp[i]) {
                case "A+":
                    array[i] = "A+";
                    pass++;
                    continue;
                case "A":
                    array[i] = "A";
                    pass++;
                    continue;
                case "A-":
                    array[i] = "A-";
                    pass++;
                    continue;
                case "B+":
                    array[i] = "B+";
                    pass++;
                    continue;
                case "B":
                    array[i] = "B";
                    pass++;
                    continue;
                case "B-":
                    array[i] = "B-";
                    pass++;
                    continue;
                case "C+":
                    array[i] = "C+";
                    pass++;
                    continue;
                case "C":
                    array[i] = "C";
                    pass++;
                    continue;
                case "C-":
                    array[i] = "C-";
                    pass++;
                    continue;
                case "D+":
                    array[i] = "D+";
                    repeat++;
                    continue;
                case "D":
                    array[i] = "D";
                    repeat++;
                    continue;
                case "E":
                    array[i] = "E";
                    repeat++;
                    continue;
                case "ab":
                    array[i] = "ab";
                    not_attend++;
                    continue;
                case "LA":
                    array[i] = "LA";
                    less_attend++;
                    continue;
            }
        }
        array[array.length - 1] = String.valueOf(less_attend);
        array[array.length - 2] = String.valueOf(not_attend);
        array[array.length - 3] = String.valueOf(repeat);
        array[array.length - 4] = String.valueOf(pass);

        return array;
    }
//-----------------------------------------------------------------------------------------------------------------------------

    //--------------------------Check Status and Fill into Array---------------------------------------------------------
    public static String[] statusIntoArray(String[] array, int lengthInt) {
        String statusTemp[] = new String[lengthInt - 4];

        for (int i = 0; i < statusTemp.length; i++) {
            if (array[i] == "A+" || array[i] == "A" || array[i] == "A-" || array[i] == "B+" || array[i] == "B" || array[i] == "B-" || array[i] == "C+" || array[i] == "C" || array[i] == "C-") {
                statusTemp[i] = "PASS";
                continue;
            } else if (array[i] == "D+" || array[i] == "D" || array[i] == "E") {
                statusTemp[i] = "REPEAT";
                continue;
            } else if (array[i] == "ab") {
                statusTemp[i] = "NOT_ATTEND";
                continue;
            } else if (array[i] == "LA") {
                statusTemp[i] = "LESS_ATTENDS";
                continue;
            } else {
                statusTemp[i] = "null";
                continue;
            }
        }
        return statusTemp;
    }

    //--------------gpa_sem into Array--------------------------------
    public static String[] dataGpa(String txt, int length) {
        String[] temp = txt.split(",");
        String[] array = new String[length];

        for (int i = 0; i < length; i++) {
            if (temp[i].equals("0")) {
                array[i] = "0";
            } else {
                array[i] = temp[i];
            }
        }

        return array;
    }
}
