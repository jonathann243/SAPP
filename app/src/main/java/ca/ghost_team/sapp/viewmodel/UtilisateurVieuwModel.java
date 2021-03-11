package ca.ghost_team.sapp.viewmodel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ca.ghost_team.sapp.model.Utilisateur;
import ca.ghost_team.sapp.repository.UtilisateurRepo;

public class UtilisateurVieuwModel extends AndroidViewModel {
    private final UtilisateurRepo userRepo;
    private final LiveData<List<Utilisateur>> Allusers;


    public UtilisateurVieuwModel(@NonNull Application application) {
        super(application);
        userRepo = new UtilisateurRepo(application);
        Allusers = userRepo.getAllUtilitisateur();
    }
    public LiveData< List<Utilisateur>> getAllusers(){
        return Allusers;
    }
    public void insertUtilisateurs(Utilisateur x){ userRepo.inserUtilisiateur(x);}

    public void deleteUtilisateurs(Utilisateur x){userRepo.deleteUtilisateur(x);}
    public void updteUtilisateurs(Utilisateur x){userRepo.updateUtilisiateur(x);}
}

