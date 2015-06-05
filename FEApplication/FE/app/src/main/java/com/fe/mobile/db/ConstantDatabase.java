package com.fe.mobile.db;

/**
 * Created by MITO on 21/05/2015.
 */
public  class ConstantDatabase {


    //DATABASE


    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="fe";


    //TABLE NOTICIA
    public static final String T_NEW="T_NOTICIA";
    public static final String NEW_ID="not_id";
    public static final String NEW_AUTOR="not_autor";
    public static final String NEW_TITULO="not_titulo";
    public static final String NEW_BAJADA="not_bajada";
    public static final String NEW_FECHA="not_fecha";
    public static final String NEW_URL="not_url";
    public static final String NEW_CUERPO="not_cuerpo";

    public static final String QUERY_CREATE_NOTICIA=
            "CREATE TABLE T_NOTICIA("+
                    "not_id integer ,"+
                    "not_titulo text,"+
                    "not_bajada text,"+
                    "not_fecha text,"+
                    "not_url text,"+
                    "not_cuerpo text)";
    public static final String QUERY_DROP_NOTICIA="DROP TABLE IF EXISTS "+T_NEW;





    //TABLE COMEDOR
    public static final String T_COMEDOR="comedor";
    public static final String COM_ID="com_id";
    public static final String COM_NOMBRE="com_nombre";
    public static final String COM_CALLE="com_calle";
    public static final String COM_BARRIO="com_barrio";
    public static final String COM_RESPONSABLE="com_responsable";


    public static final String QUERY_CREATE_COMEDOR=
            "CREATE TABLE  "+T_COMEDOR+"("+
                    COM_ID +" integer ,"+
                    COM_RESPONSABLE+ " text,"+
                    COM_NOMBRE+" text,"+
                    COM_CALLE+ " text,"+
                    COM_BARRIO + " text)";
    public static final String QUERY_DROP_COMEDOR="DROP TABLE IF EXISTS "+T_COMEDOR;




    //TABLE EVENTO
    public static final String T_EVENTO="T_EVENTO";
    public static final String EVENTO_ID="eve_id";
    public static final String EVENTO_TITULO="eve_titulo";
    public static final String EVENTO_BAJADA="eve_bajada";
    public static final String EVENTO_FECHA="eve_fecha";
    public static final String EVENTO_HORA="eve_hora";
    public static final String EVENTO_CUERPO="eve_cuerpo";
    public static final String EVENTO_URL_WEB="eve_url_web";



    public static final String QUERY_CREATE_EVENTO="CREATE TABLE "+T_EVENTO+"("+
            EVENTO_ID +" integer ,"+
            EVENTO_TITULO+" text,"+
            EVENTO_BAJADA+" text,"+
            EVENTO_FECHA +" text,"+
            EVENTO_HORA+" text,"+
            EVENTO_CUERPO+" text,"+
            EVENTO_URL_WEB+" text)";
    public static final String QUERY_DROP_EVENTO=
            "DROP TABLE IF EXISTS "+T_EVENTO;


    //APP VERSION
    public static final String T_APP="application";
    public static final String APP_ID="app_id";
    public static final String APP_VERSION="app_version";

    public static final String QUERY_CREATE_APPLICATION="CREATE TABLE "+T_APP+"("+
            APP_ID +" integer ,"+
            APP_VERSION+" text)";
    public static final String QUERY_DROP_APPLICATION=
            "DROP TABLE IF EXISTS "+T_APP;

}
