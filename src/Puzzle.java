  
import java.util.ArrayList; 
import java.util.Arrays;  
import java.util.LinkedList;
import javax.swing.JOptionPane;
public class Puzzle 
{  
    Nodo Nodo = new Nodo (); 
    private  int [][] terminal=new int[4][4];
    private  int [][] inicial=new int[4][4]; 
    ArrayList<Nodo> registroEstados = new ArrayList<Nodo>();
    ArrayList<Nodo> estadosActuales = new ArrayList<Nodo>();
    ArrayList<Nodo> sucesores = new ArrayList<Nodo>(); 
    LinkedList<Nodo> camino = new LinkedList<Nodo>(); 
    public boolean termino=false; 
    public String resultado="";
    public Puzzle(int [][] inicio, int [][] termino)
    {        
        this.inicial=inicio;
        this.terminal=termino;
        juego(); 
    }
             public void juego() 
    {  
    Nodo inicio = new Nodo();
    inicio.tablero = inicial;
    inicio.profundidad=0;
    inicio.g = casillaFuera(inicio.tablero);
    inicio.f = Manhattan(inicio.tablero) + inicio.g; 
    estadosActuales.add(inicio); 
         for (;;) 
         {
            if(inicio.profundidad==1000)
            {
               JOptionPane.showMessageDialog (null,  "Solución no encontrada\n"
                                                    +"Intenta con otra Heuristica" ,  "Error" , JOptionPane.ERROR_MESSAGE ) ;
                break;
            }
            Nodo mejor=estadosActuales.get(0);
            for (Nodo n : estadosActuales) 
            { 
                if ((mejor.f > n.f) || (mejor.f == n.f))
                {
                    mejor = n; 
                }
            } 
            estadosActuales.remove(mejor);
            registroEstados.add(mejor); 
            if (esSolucion(mejor.tablero)) 
            { 
                Nodo tmp = mejor;                
                while (tmp != null) 
                {
                    camino.addFirst(tmp);
                    tmp = tmp.nodo;
                } 
                imprimeTablero(inicio.profundidad); 
                return;
            }
            estadosActuales.addAll(generaMovimiento(mejor)); 
            inicio.profundidad++;
         }
    }
public ArrayList<Nodo> moverArriba(Nodo tabla, int []posicion)
    {  
            Nodo nuevonodo = copia(tabla.tablero);
            nuevonodo.nodo = tabla;          
            nuevonodo.tablero[posicion[0]][posicion[1]] = nuevonodo.tablero[posicion[0]-1][posicion[1]];
            nuevonodo.tablero[posicion[0] - 1][posicion[1]] =0;
            if (!existeEstado(nuevonodo.tablero)) 
            { 
                System.out.println("arriba");
                nuevonodo.g = casillaFuera(nuevonodo.tablero);
                nuevonodo.f = Manhattan(nuevonodo.tablero) + nuevonodo.g; 
	        sucesores.add(nuevonodo); 
            }
            return sucesores;
    }
    public ArrayList<Nodo> moverAbajo(Nodo tabla, int []posicion)
    {  
            Nodo nuevonodo = copia(tabla.tablero);
            nuevonodo.nodo = tabla; 
            nuevonodo.tablero[posicion[0]][posicion[1]] = nuevonodo.tablero[posicion[0]+1][posicion[1]];
            nuevonodo.tablero[posicion[0] + 1][posicion[1]] =0;
            if (!existeEstado(nuevonodo.tablero)) 
            { 
                System.out.println("abajo");
                 nuevonodo.g = casillaFuera(nuevonodo.tablero);
                nuevonodo.f = Manhattan(nuevonodo.tablero) + nuevonodo.g; 
	        sucesores.add(nuevonodo); 
            }
            return sucesores;
    }
        public ArrayList<Nodo> moverDerecha(Nodo tabla, int []posicion)
    {  
            Nodo nuevonodo = copia(tabla.tablero);
            nuevonodo.nodo = tabla;
            
            nuevonodo.tablero[posicion[0]][posicion[1]] = nuevonodo.tablero[posicion[0]][posicion[1]+1];
            nuevonodo.tablero[posicion[0]][posicion[1]+1] =0;
            if (!existeEstado(nuevonodo.tablero)) 
            { 
                nuevonodo.g = casillaFuera(nuevonodo.tablero);
                nuevonodo.f = Manhattan(nuevonodo.tablero) + nuevonodo.g; 
	        sucesores.add(nuevonodo);
                System.out.println("derecha"); 
            }
            return sucesores;
    }
         public ArrayList<Nodo> moverIzquierda(Nodo tabla, int []posicion)
    {  
            Nodo nuevonodo = copia(tabla.tablero);
            nuevonodo.nodo = tabla; 
            nuevonodo.tablero[posicion[0]][posicion[1]] = nuevonodo.tablero[posicion[0]][posicion[1]-1];
            nuevonodo.tablero[posicion[0]][posicion[1]-1] =0;
            if (!existeEstado(nuevonodo.tablero)) 
            { 
                nuevonodo.g = casillaFuera(nuevonodo.tablero);
                nuevonodo.f = Manhattan(nuevonodo.tablero) + nuevonodo.g; 
	        sucesores.add(nuevonodo); 
            }
            return sucesores;
    }  
    public int casillaFuera(int[][] nextTab) 
    {
        int h = 0;
        if (nextTab[0][0] != terminal[0][0] && nextTab[0][0] != 0) {
            h++;
        }
        if (nextTab[0][1] != terminal[0][1] && nextTab[0][1] != 0) {
            h++;
        }
        if (nextTab[0][2] != terminal[0][2] && nextTab[0][2] != 0) {
            h++;
        }
        if (nextTab[0][3] != terminal[0][3] && nextTab[0][3] != 0) {
            h++;
        }
        if (nextTab[1][0] != terminal[1][0] && nextTab[1][0] != 0) {
            h++;
        }
        if (nextTab[1][1] != terminal[1][1] && nextTab[1][1] != 0) {
            h++;
        }
        if (nextTab[1][2] != terminal[1][2] && nextTab[1][2] != 0) {
            h++;
        }
        if (nextTab[1][3] != terminal[1][3] && nextTab[1][3] != 0) {
            h++;
        }
        if (nextTab[2][0] != terminal[2][0] && nextTab[2][0] != 0) {
            h++;
        }
        if (nextTab[2][1] != terminal[2][1] && nextTab[2][1] != 0) {
            h++;
        }
        if (nextTab[2][2] != terminal[2][2] && nextTab[2][2] != 0) {
            h++;
        }
        if (nextTab[2][3] != terminal[2][3] && nextTab[2][3] != 0) {
            h++;
        }
          if (nextTab[3][0] != terminal[3][0] && nextTab[3][0] != 0) {
            h++;
        }
        if (nextTab[3][1] != terminal[3][1] && nextTab[3][1] != 0) {
            h++;
        }
        if (nextTab[3][2] != terminal[3][2] && nextTab[3][2] != 0) {
            h++;
        }
        if (nextTab[3][3] != terminal[3][3] && nextTab[3][3] != 0) {
            h++;
        }
        return h;
    }
    public Nodo copia(int[][] actual) 
    { 
        Nodo retorno = new Nodo(); 
        for (int i = 0; i < 4; i++)  
            for (int j = 0; j < 4; j++) 
                retorno.tablero[i][j] = actual[i][j]; 
        return retorno;
    }
 
     private ArrayList<Nodo> generaMovimiento(Nodo actual) 
     { 
        int[] posicionvacia = posicionVacia(actual.tablero); 
        if (posicionvacia == null) 
            return null;
        if (posicionvacia[0]>0) 
            moverArriba(actual,posicionvacia);           
        if (posicionvacia[0]<3) 
            moverAbajo(actual,posicionvacia);   
        if (posicionvacia[1]>0) 
            moverIzquierda(actual,posicionvacia);  
        if (posicionvacia[1]<3) 
            moverDerecha(actual,posicionvacia);  
      return sucesores;
    }

    public boolean existeEstado(int [][] estado)
    {  
         for(Nodo ncerrado : registroEstados)
        {
            if(Arrays.deepEquals(estado,ncerrado.tablero))
            { 
             return true;
            } 
        } 
        return false;
    }
    private boolean esSolucion (int [][] actual)
    {    
      if(Arrays.deepEquals(actual,terminal))
            {
            termino=true;
            return true;
            } 
      return false;
    }    
        private int[] posicionVacia(int [][] tabla) 
    {   
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {  
                if(tabla[i][j]==0)
                { 
                    int pos []={i,j};   
                    return pos;
                }
            }
        }          
        return null;
    }
    private String  imprimeTablero(int  prof) 
    {  
       resultado="Profunididad".toString()+prof+"\n";      
       int movimiento=0;
       for(Nodo recorrido : camino) 
        {
            resultado=resultado+"Movimiento:"+movimiento+"\n"; 
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                { 
                    resultado=resultado+recorrido.tablero[i][j]+"\t"; 
                }
                resultado=resultado+"\n";
            } 
            resultado=resultado+"\n";
            movimiento++;
        } 
        return resultado;
    }
public String imprime() 
    {
         return resultado;
    }
 public int Manhattan(int[][] tablero_n) 
 {
        int h = 0;  
          for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                { 
                   if (tablero_n[i][j] != terminal[i][j] && tablero_n[i][j] != 0) 
                   {
                   int distancia = distanciaManhattan(i, j, tablero_n[i][j]);
                   h += distancia;
                   }
                }               
            } 
        return h;
    }
    public int[] busca(int actual) 
    {     
        int i = 0, j = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) 
            {
                if (terminal[i][j] == actual) {
                    int[] retorno = new int[2];
                    retorno[0] = i;
                    retorno[1] = j;
                    return retorno;
                }
            }
        }
        return null;
    }
    private int distanciaManhattan(int i, int j, int valor) 
    {
    int[] pos = busca(valor);
    //0,2
    //2,0 -2+2
    int hf=pos[0];
    int vf=pos[1];
    return Math.abs(i - hf) + Math.abs(j-vf);
    } 
//
//    private int regresoDirecto(int[][] tablero) 
//    {
//        int x=0;
//        int i = 0, j = 0;
//        for (i = 0; i < 4; i++) 
//        {
//            for (j = 0; j < 4; j++) 
//            {
//                if (i>0) 
//                {
//                  if(terminal[i][j]==tablero[i-1][j])
//                      
//                }
//                if (i<3) 
//                {
//                    if(terminal[i][j] == tablero[i + 1][j])
//                }       
//                if (j>0) 
//                {
//                    if(terminal[i][j] == tablero[i][j - 1])
//                }    
//                if (j<3) 
//                {
//                    if(terminal[i][j] == tablero[i][j + 1])
//                }
//            }        
//    }
//   }
}
