package Proiect_PS.service;

import Proiect_PS.dto.UserData;
import Proiect_PS.dto.UserPasswordData;
import Proiect_PS.dto.UserUsernameData;
import Proiect_PS.model.User;

import java.util.List;
/**
 * Interfața ServiceUserInterface specifică metodele necesare pentru gestionarea utilizatorilor
 * într-un sistem. Aceasta include operațiuni pentru inserarea, ștergerea, actualizarea și căutarea utilizatorilor,
 * precum și actualizarea parolei lor.
 */
public interface ServiceUserInterface {
    /**
     * Inserează un nou utilizator în sistem.
     *
     * @param userData Datele utilizatorului pentru inserare.
     * @return new User.
     */
    User insertUser(UserData userData);
    /**
     * Șterge un utilizator din sistem bazat pe username.
     *
     * @param userData Datele necesare pentru identificarea utilizatorului de șters.
     * @return Utilizatorul șters.
     */
    User deleteUser(UserUsernameData userData);
    /**
     * Actualizează informațiile unui utilizator existent.
     *
     * @param userData Datele noi ale utilizatorului pentru actualizare.
     * @return user updated.
     */
    User updateUser(UserData userData);
    /**
     * Găsește un utilizator în sistem bazat pe username.
     *
     * @param userData Datele necesare pentru identificarea utilizatorului.
     * @return Utilizatorul găsit sau null dacă nu există.
     */
    User findUserByUsername(UserUsernameData userData);
    /**
     * Returnează o listă cu toți utilizatorii din sistem.
     *
     * @return Lista utilizatorilor.
     */
    List<User> findAll();
    /**
     * Actualizează parola unui utilizator.
     *
     * @param userPasswordData Datele necesare pentru actualizarea parolei.
     * @return user-ul cu parola actualizata.
     */
    User updatePassword(UserPasswordData userPasswordData);
}
