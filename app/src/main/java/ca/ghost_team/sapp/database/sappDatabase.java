package ca.ghost_team.sapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

import ca.ghost_team.sapp.BaseApplication;
import ca.ghost_team.sapp.R;
import ca.ghost_team.sapp.dao.AnnonceDao;
import ca.ghost_team.sapp.dao.AnnonceFavorisDao;
import ca.ghost_team.sapp.dao.UtilisateurDao;
import ca.ghost_team.sapp.model.Annonce;
import ca.ghost_team.sapp.model.AnnonceFavoris;
import ca.ghost_team.sapp.model.Utilisateur;
import conv.Conversion;

@TypeConverters({Conversion.class})//Pour dire que notre base de donnne fait la covertion de chaque date en long
@Database(entities = {Annonce.class, Utilisateur.class, AnnonceFavoris.class}, version = 1, exportSchema = false)
public abstract class sappDatabase extends RoomDatabase {
    public static sappDatabase INSTANCE;
    public abstract AnnonceDao annonceDao();
    public abstract UtilisateurDao utilisateurDao();
    public abstract AnnonceFavorisDao AnnonceFavorisDao();

    public static synchronized sappDatabase getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),sappDatabase.class, BaseApplication.NAME_DB)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private AnnonceDao annonceDao;
        private UtilisateurDao utilisateurDao;

        public PopulateDbAsyncTask(sappDatabase instance) {

            utilisateurDao=instance.utilisateurDao();
            annonceDao= instance.annonceDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            utilisateurDao.insertallUtilisateur(new Utilisateur(
                    "Josue Lubaki",
                    "Lubaki",
                    "Heroes",
                    "jojo@gmail.com"));
            utilisateurDao.insertallUtilisateur(new Utilisateur(
                    "Ismael",
                    "Coulibaly",
                    "hybs",
                    "ismael@gmail.com"));
            utilisateurDao.insertallUtilisateur(new Utilisateur(
                    "Jonathan",
                    "Kanyinda",
                    "PC JO",
                    "jonathan@gmail.com"));

            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.collection,
                    "Ma collection",
                    "Je te vends mes plus beaux vetements",
                    150,
                    new Date(),
                    false,
                    3
                    ));

            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.chemise,
                    "Ma Chemise",
                    "Ma chemise blue",
                    50,
                    new Date(),
                    true,
                    1));
            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.img_splash2,
                    "Ma Collection",
                    "Je te vends mes plus belle robes de soirée",
                    295,
                    new Date(),
                    false,
                    2));
            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.chemise,
                    "Ceinture",
                    "Tu aimes les ceintures de marque ?",
                    120,
                    new Date(),
                    false,
                    3));
            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.collection,
                    "Jogging gris",
                    "Pret pour le sport ?",
                    45,
                    new Date(),
                    true,
                    1));
            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.chemise,
                    "T-shirt",
                    "Je te jure que tu vas l'adorer",
                    25,
                    new Date(),
                    false,
                    3));
            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.culotte2,
                    "Culotte",
                    "je l'aime bien pour le BasketBall",
                    55,
                    new Date(),
                    false,
                    2));
            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.collection,
                    "Veste",
                    "Tu veux être présentable ?",
                    350,
                    new Date(),
                    true,
                    2));
            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.chemise,
                    "T-shirt",
                    "Je te jure que tu vas l'adorer",
                    25,
                    new Date(),
                    false,
                    3));
            annonceDao.insertAnnonce(new Annonce(
                    R.drawable.chemise,
                    "Chaussette",
                    "Mes chaussettes de Noël",
                    5,
                    new Date(),
                    false,
                    1));

            return null;
        }
    }
}
