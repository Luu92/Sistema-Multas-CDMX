/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOModel;

/**
 *
 * @author DevLuu
 */
public class BinTarjetas {
    
    int binBancomer[] = {4101,4180,4413,4555,4943,5420,5429,5440,5470,5471};
    
    int binBanamex[] = {4053,4108,4207,4353,4391,4415,4540,4544,4552,4901,4912,4984,
                        5127,5128,5180,5188,5202,5206,5221,5254,5282,5288,5290,5307,5446,
                        5450,5482,5491};
    
    int binHsbc[] = {4134,4165,4260,4524,4568,5409,5412};
    
    int binSantander[] = {4915,4932,4941,5408,5453,5549};
    
    int binBanorte[] = {4913,4931,5445};
    
    int binInbursa[] = {4020,4043,4049,4314,4629,4737};
    
    public boolean identificaBanco(int tarjeta){
        if(validaBbva(tarjeta)){
          return true;
        }else if(validaBnmx(tarjeta)){
            return true;
        }else if(validaHsbc(tarjeta)){
            return true;
        }else if(validaBanorte(tarjeta)){
            return true;
        }else if(validaSantander(tarjeta)){
            return true;
        }else if(validaInbursa(tarjeta)){
            return true;
        }
        return false;
    }
    
    public boolean validaBbva(int tarjeta){
        for (int x = 0; x < binBancomer.length; x++) {
           if (binBancomer[x] == tarjeta) { 
              return true;
           }
        }
    return false;
    }
    
    public boolean validaBnmx(int tarjeta){
        for (int x = 0; x < binBanamex.length; x++) {
           if (binBanamex[x] == tarjeta) {
              return true;
           }
        }
    return false;
    }
    
    public boolean validaHsbc(int tarjeta){
        for (int x = 0; x < binHsbc.length; x++) {
           if (binHsbc[x] == tarjeta) {
              return true;
           }
        }
    return false;
    }    

    public boolean validaBanorte(int tarjeta){
        for (int x = 0; x < binBanorte.length; x++) {
           if (binBanorte[x] == tarjeta) {
              return true;
           }
        }
    return false;
    }
    
    public boolean validaSantander(int tarjeta){
        for (int x = 0; x < binSantander.length; x++) {
           if (binSantander[x] == tarjeta) {
              return true;
           }
        }
    return false;
    }
    
    public boolean validaInbursa(int tarjeta){
        for (int x = 0; x < binInbursa.length; x++) {
           if (binInbursa[x] == tarjeta) {
              return true;
           }
        }
    return false;
    }      
}
