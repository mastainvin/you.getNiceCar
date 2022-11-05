package com.jee.yougetnicecar;

import com.jee.yougetnicecar.exceptions.NotAdminException;
import com.jee.yougetnicecar.exceptions.NotUserException;
import com.jee.yougetnicecar.models.Role;
import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.ui.Model;

public class Utils {
    public static void checkAdmin(Model model) {
        if(!model.containsAttribute("utilisateur")) {
            throw new NotAdminException();
        }
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
        assert utilisateur != null;
        if(utilisateur.getRole() != Role.ADMIN) {
            throw new NotAdminException();
        }
    }
    public static void checkUser(Model model) {
        if(!model.containsAttribute("utilisateur")) {
            throw new NotUserException();
        }
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
        assert utilisateur != null;
        if(utilisateur.getRole() != Role.USER) {
            throw new NotUserException();
        }
    }
}
