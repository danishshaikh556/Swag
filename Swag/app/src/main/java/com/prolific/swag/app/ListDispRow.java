package com.prolific.swag.app;

/**
 * Created by Danish556 on 3/30/14.
 */
public class ListDispRow {
    private String List_Disp_Tile_Row;
    private String List_Disp_Author_Row;
    private int    List_Disp_id_Row;
    public ListDispRow(String title,String author,int idOfBook)
    {
        this.List_Disp_Tile_Row   = title;
        this.List_Disp_Author_Row = author;
        this.List_Disp_id_Row     = idOfBook;
    }


    public String getList_Disp_Tile_Row() {
        return List_Disp_Tile_Row;
    }

    public void setList_Disp_Tile_Row(String list_Disp_Tile_Row) {
        List_Disp_Tile_Row = list_Disp_Tile_Row;
    }

    public String getList_Disp_Author_Row() {
        return List_Disp_Author_Row;
    }

    public void setList_Disp_Author_Row(String list_Disp_Author_Row) {
        List_Disp_Author_Row = list_Disp_Author_Row;
    }

    public int  getList_Disp_id_Row(){return List_Disp_id_Row;}

    public void setList_Disp_id_Row(int idOfBook){this.List_Disp_id_Row =idOfBook;}
}
