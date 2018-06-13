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
}
