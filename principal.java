import java.util.ArrayList;

/**
 * Autor: Cristhian Enrique
 */
public class principal {
    //Se utilizó una matriz para representar la población de targets
    public static void main(String[] args) {
        ArrayList<Double> mejoresValores = new ArrayList<Double>();
        System.out.println("Algoritmo de Evolución Diferencial!");
        ED ed = new ED(10);
        ed.initTargets();
        ed.mostrarTargets();
        System.out.println(":::::::::::::::::::::::::::::");
        int gen= 1;
        int genMax = 15;
        double f =0.01;
        double cr = 1;

        while(gen<=genMax){

            String elegidos="";
            //Por cada target se formará el Vo y despues el trial
            double[] vectorRuido = new double[3];
            double[] r0 = new double[3];
            double[] r1 = new double[3];
            double[] r2 = new double[3];
            //0 = 0.1   1.0   0.3
            for (int i = 0; i <ed.getTargets().length-1; i++) {
                //Se seleccionan los 3 vectores al azar
                elegidos = ed.getTresR(i); //Se obtienen los 3 valores de r para elegir vectores
                r0 = ed.getTargets()[Integer.parseInt(""+elegidos.charAt(0))];
                r1 = ed.getTargets()[Integer.parseInt(""+elegidos.charAt(1))];
                r2 = ed.getTargets()[Integer.parseInt(""+elegidos.charAt(2))];
                System.out.println("Para el vector " + i);
                System.out.println("Numeros: "+elegidos);
                mostrarVector(r0);
                mostrarVector(r1);
                mostrarVector(r2);

                //Se crea el vector ruido con los 3 vectores seleccionados
                for (int j = 0; j <= vectorRuido.length-1; j++) {
                    double val = r0[j] + f * (r1[j]-r2[j]);
                    vectorRuido[j] = (double)Math.round(val * 1000d) / 1000d;
                }
                System.out.println("Vector ruido: ");
                mostrarVector(vectorRuido);

                //Se crea el vector trail
                double volado;
                double[] u = new double[vectorRuido.length];
                for (int j = 0; j <=vectorRuido.length-1; j++) {
                    volado = Math.random();
                    if(volado < cr){
                        //Se asigna el valor del mutante
                        u[j] = vectorRuido[j];
                    }
                    else{
                        //Se asigna el valor del original
                        u[j] = ed.getTargets()[i][j];
                    }
                }

                System.out.println("Vector trial formado: ");
                mostrarVector(u);
                //Si el vector trail tiene mejor aptitud se remplaza el target y se coloca el trail en su
                //posición
                if(ed.getAptitud(u)  <= ed.getAptitud(ed.getTargets()[i])){
                    System.out.println("El trial tiene una aptitud de " + ed.getAptitud(u)
                    + " a diferencia del target que tiene " + ed.getAptitud(ed.getTargets()[i]));
                    System.out.println("El trial reemplaza al target");
                    ed.replaceVector(i,u);
                }

            }
            System.out.println("TARGETS PARA SIGUIENTE GENERACIÓN");
            ed.mostrarTargets();
            mejoresValores.add(ed.getMejor(ed.getTargets()));
            gen++;
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        }

        System.out.println("LISTA DE MEJORES APTITUDES POR GENERACIÓN: ");
        for (int i = 0; i <=mejoresValores.size()-1; i++) {
            System.out.println("Generación " + i + " = " + mejoresValores.get(i));
        }
    }
    public static void mostrarVector(double[] vector){
        for (int i = 0; i <= vector.length-1; i++) {
            System.out.print(vector[i] + " | ");
        }
        System.out.println();
    }
}