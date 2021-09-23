
/**
 * Autor: Cristhian Enrique
 */
public class ED {
    public double[][] targets;

    //0.5 0.1
    public ED(int numTargets){
        this.targets = new double[numTargets][3];

    }

    public double[][] getTargets() {
        return targets;
    }

    //Este método retorna 3 números diferentes a la semilla y que no esten repetidos
    public String getTresR(int semilla){
        String elegidos = ""; //325  321  783
        for (int i = 1; i <=3;) {
            int v1 = (int)Math.floor(Math.random()*(targets.length)+0);
            if(v1 != semilla && !elegidos.contains(String.valueOf(v1))){
                elegidos += String.valueOf(v1);
                i++;
            }
        }
        return elegidos;
    }
    public void initTargets(){
        Aleatorios.setRseed(0.5f);
        Aleatorios.randomize();
        for (int i = 0; i <=targets.length-1; i++) {
            double x;
            for (int j = 0; j <= targets[i].length-1; j++) {
                x = Aleatorios.rnd(-3,3);
                targets[i][j] = x;
            }
        }
    }

    public double getMejor(double[][] tar){
        double b = 9999;
        for (int i = 0; i <=tar.length-1; i++) {
            if(getAptitud(tar[i])<b){
                b = getAptitud(tar[i]);
            }
        }
        return b;
    }
    public void mostrarTargets(){
        for (int i = 0; i <=targets.length-1; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j <=targets[i].length-1; j++) {
                System.out.print(targets[i][j] + " | ");
            }
            System.out.println("  Aptitud: " + getAptitud(targets[i]));

        }
    }
    //Sirve para reemplzar un vector por otro en los targets, para la parte de evaluar aptitud de target y trail
    public void replaceVector(int indice,double[] vector){
        targets[indice] = vector;
    }

    public double getAptitud(double[] vector){
        return (vector[0] * vector[0]) +
                (vector[1] * vector[1]) +
                (vector[2] * vector[2]);
    }
}
