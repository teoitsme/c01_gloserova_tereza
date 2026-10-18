package rasterize;

import raster.Raster;

/**
 * Bresenhamův algoritmus pro kreslení úsečky.
 * Celočíselná aritmetika (rychlý výpočet bez desetinných čísel).
 */
public class LineRasterizerBresenham extends LineRasterizer{

    public LineRasterizerBresenham(Raster raster) {
        // super(): Volá konstruktor rodičovské třídy (LineRasterizer).
        // Předává mu plátno (raster), aby rodič věděl, kam se bude kreslit.
        super(raster);
    }

    @Override
    public void drawLine (int x1, int y1, int x2, int y2, int color) {
        // Math.abs(): Ano, absolutní hodnota. Zaručí, že vzdálenost bude vždy kladná.
        // dx, dy: Vzdálenost mezi body na ose X a Y.
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        // sx, sy: Směr kroku (1 = doprava/dolů, -1 = doleva/nahoru).
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        // err: Chybová proměnná (určuje, kdy se má čára "zlomit" do dalšího pixelu).
        int err = dx - dy;

        while (true) {
            // Nakreslí pixel
            raster.setPixel(x1, y1, color);

            // Konec smyčky: Jsme v cílovém bodě
            if (x1 == x2 && y1 == y2) break;

            // OPRAVA: V tvém kódu chyběla hvězdička pro násobení (2 * err)
            int e2 = 2 * err;

            // Krok v ose X
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            // Krok v ose Y
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
}