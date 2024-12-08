package bingo.art;

public class Blocks{
    public static void grass(int layer){
        switch(layer){
            case 1:
                System.out.print("        ;.");
                return;
            case 2:
                System.out.print("  .  ..   ");
                return;
            case 3:
                System.out.print(" .   .   .");
                return;
            case 4:
                System.out.print("    .  :. ");
                return;
            default:
                return;
        }
    }

    public static void grass2(int layer){
        switch(layer){
            case 1:
                System.out.print("    ,  .: ");
                return;
            case 2:
                System.out.print(":.   .:  .");
                return;
            case 3:
                System.out.print(" :. .  :. ");
                return;
            case 4:
                System.out.print("   .  ,`  ");
                return;
            default:
                return;
        }
    }

    public static void empty(int layer){
        switch(layer){
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.print("          ");
                return;
            default:
                return;
        }
    }

    public static void tree(int layer){
        switch(layer){
            case 1:
                System.out.print("   .``.``.");
                return;
            case 2:
                System.out.print(" .`  `. .`");
                return;
            case 3:
                System.out.print("  -...`.` ");
                return;
            case 4:
                System.out.print("    }{    ");
                return;
            default:
                return;
        }
    }

    public static void locked(int layer){
        switch(layer){
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.print("::::::::::");
                return;
            default:
                return;
        }
    }
    public static void water(int layer){
        switch(layer){
            case 1:
                System.out.print("--__---_--");
                return;
            case 2:
                System.out.print("          ");
                return;
            case 3:
                System.out.print("--__---_--");
                return;
            case 4:
                System.out.print("          ");
                return;
            default:
                return;
        }
    }
    public static void wall(int layer){
        switch(layer){
            case 1:
                System.out.print("__|_.|._|_");
                return;
            case 2:
                System.out.print("|--|.._|__");
                return;
            case 3:
                System.out.print("._|--|..|_");
                return;
            case 4:
                System.out.print("|__.|.|.._");
                return;
            default:
                return;
        }
    }

    public static void shrine(int layer){
        switch(layer){
            case 1:
                System.out.print("  .====.  ");
                return;
            case 2:
                System.out.print(".| .::. |.");
                return;
            case 3:
                System.out.print(".`-____-`.");
                return;
            case 4:
                System.out.print("          ");
                return;
            default:
                return;
        }
    }

    public static void enemy(int layer){
        switch(layer){
            case 1:
                System.out.print("          ");
                return;
            case 2:
                System.out.print(" 1.----.1 ");
                return;
            case 3:
                System.out.print(":` <  > `:");
                return;
            case 4:
                System.out.print(" `------` ");
                return;
            default:
                return;
        }
    }

    public static void elite(int layer){
        switch(layer){
            case 1:
                System.out.print(" 1.____.1 ");
                return;
            case 2:
                System.out.print(" `.*__*.` ");
                return;
            case 3:
                System.out.print("  |____|  ");
                return;
            case 4:
                System.out.print("          ");
                return;
            default:
                return;
        }
    }

    public static void boss(int layer){
        switch(layer){
            case 1:
                System.out.print("_.-`--`-._");
                return;
            case 2:
                System.out.print("`|*\\__/*|`");
                return;
            case 3:
                System.out.print(" `|VVVV|` ");
                return;
            case 4:
                System.out.print("  |AAAA|  ");
                return;
            default:
                return;
        }
    }

    public static void loot(int layer){
        switch(layer){
            case 1:
                System.out.print("  ______  ");
                return;
            case 2:
                System.out.print(" |=|``|=| ");
                return;
            case 3:
                System.out.print(".|______|.");
                return;
            case 4:
                System.out.print("`::::::::`");
                return;
            default:
                return;
        }
    }

}