/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Enum.TipoServico;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author n226814168
 */
public class Util {

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static TipoServico obterTipoServico(String nome) {
        TipoServico retorno = null;
        switch (nome) {
            case "Limpeza":
                retorno = TipoServico.Limpeza;
                break;
            case "Manutenção":
                retorno = TipoServico.Manutencao;
                break;
            case "Mudança":
                retorno = TipoServico.Mudanca;
                break;
            case "PetShop":
                retorno = TipoServico.PetShop;
                break;
            case "Acompanhante":
                retorno = TipoServico.Acompanhante;
                break;
            case "Garçom":
                retorno = TipoServico.Garcom;
                break;
            case "Encomenda":
                retorno = TipoServico.Emcomenda;
                break;
        }

        return retorno;
    }

    public static TipoServico obterTipoServico(int num) {
        TipoServico retorno = null;
        switch (num) {
            case 0:
                retorno = TipoServico.Limpeza;
                break;
            case 1:
                retorno = TipoServico.Manutencao;
                break;
            case 2:
                retorno = TipoServico.Mudanca;
                break;
            case 3:
                retorno = TipoServico.PetShop;
                break;
            case 4:
                retorno = TipoServico.Acompanhante;
                break;
            case 5:
                retorno = TipoServico.Garcom;
                break;
            case 6:
                retorno = TipoServico.Emcomenda;
                break;
        }

        return retorno;
    }

    public static String obterTipoServicoPorParametro(TipoServico tipoServico) {
        String retorno = "";
        switch (tipoServico) {
            case Limpeza:
                retorno = "Limpeza";
                break;
            case Manutencao:
                retorno = "Manutenção";
                break;
            case Mudanca:
                retorno = "Mudança";
                break;
            case PetShop:
                retorno = "PetShop";
                break;
            case Acompanhante:
                retorno = "Acompanhante";
                break;
            case Garcom:
                retorno = "Garçom";
                break;
            case Emcomenda:
                retorno = "Encomenda";
                break;
        }

        return retorno;
    }

    public static int obterintTipoServicoPorParametro(TipoServico tipoServico) {
        int retorno = 0;
        switch (tipoServico) {
            case Limpeza:
                retorno = 0;
                break;
            case Manutencao:
                retorno = 1;
                break;
            case Mudanca:
                retorno = 2;
                break;
            case PetShop:
                retorno = 3;
                break;
            case Acompanhante:
                retorno = 4;
                break;
            case Garcom:
                retorno = 5;
                break;
            case Emcomenda:
                retorno = 6;
                break;
        }

        return retorno;
    }

    public static void getHashMap(List<Map<String, Object>> row, ResultSet rs_SubItemType) throws SQLException {

        ResultSetMetaData metaData = rs_SubItemType.getMetaData();
        int colCount = metaData.getColumnCount();

        while (rs_SubItemType.next()) {
            Map<String, Object> columns = new HashMap<String, Object>();
            for (int i = 1; i <= colCount; i++) {
                columns.put(metaData.getColumnLabel(i), rs_SubItemType.getObject(i));
            }

            row.add(columns);
        }
    }

    public static void showMessage(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarCpf(String CPF) {
// considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
// Calculo do 1o. Digito Verificador
        sm = 0;
        peso = 10;
        for (i = 0; i < 9; i++) {
// converte o i-esimo caractere do CPF em um numero:
// por exemplo, transforma o caractere '0' no inteiro 0         
// (48 eh a posicao de '0' na tabela ASCII)         
            num = (int) (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) {
            dig10 = '0';
        } else {
            dig10 = (char) (r + 48); // converte no respectivo caractere numerico
        }
// Calculo do 2o. Digito Verificador
        sm = 0;
        peso = 11;
        for (i = 0; i < 10; i++) {
            num = (int) (CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) {
            dig11 = '0';
        } else {
            dig11 = (char) (r + 48);
        }

// Verifica se os digitos calculados conferem com os digitos informados.
        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
            return (true);
        } else {
            return (false);
        }
    }

}
