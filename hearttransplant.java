public class HeartTransplant {   
    private Person[] listOfPatients;    
    private SurvivabilityByAge[] survivabilityByAge;   
    private SurvivabilityByCause[] survivabilityByCause;

    public HeartTransplant() {
        this.listOfPatients = null;
        this.survivabilityByAge = null;
        this.survivabilityByCause = null;
        
    }

    public int addPerson(Person p, int arrayIndex) {
        if (arrayIndex > listOfPatients.length-1) {
            return (-1);
        }
        else {
            listOfPatients[arrayIndex] = p;
            return (0);
        }
    }

    public int readPersonsFromFile(int numberOfLines) {
        listOfPatients = new Person[numberOfLines];
        for(int i=0;i<numberOfLines;i++){
            int id = StdIn.readInt();
            int ethnicity = StdIn.readInt();
            int gender = StdIn.readInt();
            int age = StdIn.readInt();
            int cause = StdIn.readInt();
            int urgency = StdIn.readInt();
            int stateOfHealth = StdIn.readInt();    
            Person patient = new Person(id,ethnicity,gender,age,cause,urgency,stateOfHealth);
            addPerson(patient, i);
        }
        return (numberOfLines);
    }

    public int readSurvivabilityRateByAgeFromFile (int numberOfLines) {
        survivabilityByAge = new SurvivabilityByAge[numberOfLines];
        for(int i=0;i<numberOfLines; i++){
            int age = StdIn.readInt();
            int years = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByAge[i] = new SurvivabilityByAge(age,years,rate);
        }
        return (numberOfLines);
    }

    public int readSurvivabilityRateByCauseFromFile (int numberOfLines) {
        survivabilityByCause = new SurvivabilityByCause[numberOfLines];
        for(int i=0;i<numberOfLines; i++){
            int cause = StdIn.readInt();
            int years = StdIn.readInt();
            double rate = StdIn.readDouble();
            survivabilityByCause[i] = new SurvivabilityByCause(cause,years,rate);
        }
        return (numberOfLines);
    }

    public Person[] getListOfPatients() {
        return listOfPatients;
    } 

    public SurvivabilityByAge[] getSurvivabilityByAge() {
        return survivabilityByAge;
    }

    public SurvivabilityByCause[] getSurvivabilityByCause() {
        return survivabilityByCause;
    }

    public Person[] getPatientsWithAgeAbove(int age) {
        int numPatient = 0;
        for(int i=0;i<listOfPatients.length;i++){
            if(listOfPatients[i].getAge()>age){
              numPatient++;
          }
      }
        Person[] patientswithageabove = new Person[numPatient];
        if (numPatient!=0){
            int num = 0;
            for (int i = 0; i < listOfPatients.length; i++) {
                if (listOfPatients[i].getAge() > age) {
                    patientswithageabove[num] = listOfPatients[i];
                    num++;
              }
          }
            return (patientswithageabove);
      }
        else {
            return (null);
      }
  }

    public Person[] getPatientsByStateOfHealth(int state) {

        int numStatePatients = 0;
        for(int i = 0; i < listOfPatients.length;i++){
            if(listOfPatients[i].getStateOfHealth() == state){
                numStatePatients++;
          }
      }
        Person[] statePatient = new Person[numStatePatients];
        if (numStatePatients!=0){
            int num = 0;
            for(int i=0; i < listOfPatients.length; i++){
                if(listOfPatients[i].getStateOfHealth() == state){
                    statePatient[num] = listOfPatients[i];
                    num++;
              }
          }
            return (statePatient);
      }
        else {
            return (null);
      }    

  }

    public Person[] getPatientsByHeartConditionCause(int cause) {

        int numCausePatients = 0;
        for(int i = 0; i < listOfPatients.length;i++){
            if(listOfPatients[i].getCause() == cause){
                numCausePatients++;
          }
      }
        Person[] causePatient = new Person[numCausePatients];
        if (numCausePatients!=0){
            int num = 0;
            for(int i=0; i < listOfPatients.length; i++){
                if(listOfPatients[i].getCause() == cause){
                    causePatient[num] = listOfPatients[i];
                    num++;
              }
          }
            return (causePatient);
      }
        else {
            return (null);
      }    
  }

    public Person[] match(int numberOfHearts) {
        Person[] heartPatients = new Person[numberOfHearts];
        if(numberOfHearts >= listOfPatients.length){
            return listOfPatients;
      }
        if(numberOfHearts == 0){
            return null;
      }
        if (numberOfHearts <= listOfPatients.length){
            for (int i = 0; i< numberOfHearts; i++){
            heartPatients[i] = listOfPatients[i];
          }
      }
            else {
                Person amt = listOfPatients[0];
                for (int j = 0; j<listOfPatients.length; j++){
                    for (int k = j+1; k< listOfPatients.length; k++){
                        if(listOfPatients[j].getUrgency()>listOfPatients[k].getUrgency()){
                            amt = listOfPatients[j];
                            listOfPatients [j] = listOfPatients [k];
                            listOfPatients [k] = amt;
            
                            for (int h = 0; h<numberOfHearts; h++){
                                heartPatients[h] = listOfPatients[h];
                      }
                   }
                }
             }
          }        
        return heartPatients;
  }

    public static void main (String[] args) {

        HeartTransplant ht = new HeartTransplant();
    
        int numberOfLines = StdIn.readInt();
        int numberOfReadings = ht.readPersonsFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " patients read from file.");
 
        numberOfLines = StdIn.readInt();
        numberOfReadings = ht.readSurvivabilityRateByAgeFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " survivability rates by age lines read from file.");

        numberOfLines = StdIn.readInt();
        numberOfReadings = ht.readSurvivabilityRateByCauseFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " survivability rates by cause lines read from file.");
        
        for (Person p : ht.getListOfPatients()) {
            StdOut.println(p);
      }

        for (SurvivabilityByAge rate : ht.getSurvivabilityByAge()) {
            StdOut.println(rate);
      }

        for (SurvivabilityByCause rate : ht.getSurvivabilityByCause()) {
            StdOut.println(rate);

      }
   }
}