package exception.model.vol;

import model.vol.Vol;

public class VolException extends Exception{
    
    Vol vol ; 

    public Vol getVol() {
        return this.vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public VolException(){
        super() ;
    }

    public VolException(String message){
        super(message) ;
    }

    public VolException(Vol vol, String message){
        super(message) ;
        setVol(vol);
    }

}
