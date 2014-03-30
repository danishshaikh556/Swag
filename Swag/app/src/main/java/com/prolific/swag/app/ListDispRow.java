package com.prolific.swag.app;

/**
 * Created by Danish556 on 3/30/14.
 */
public class ListDispRow {
    private String List_Disp_Tile_Row;
    private String List_Disp_Author_Row;

    public ListDispRow(String title,String author)
    {
        this.List_Disp_Tile_Row   = title;
        this.List_Disp_Author_Row = author;
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
}
