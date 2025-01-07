package view;

import service.TodolistService;
import util.InputUtil;

public class View {
    private TodolistService todolistService;

    public View(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    public void showTodoList(){
        while(true){
            this.todolistService.showTodoList();

            System.out.println("MENU: ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("99. Keluar");

            var input = InputUtil.input("Pilih ");

            if(input.equals("1")){
                addTodoList();
            } else if ( input.equals("2")){
                removeTodoList();
            } else if ( input.equals("99")){
                break;
            } else {
                System.out.println("Pilihan tidak dimengerti!");
            }
        }
    }

    public void addTodoList(){
        System.out.println("MENAMBAH TODO LIST");

        var list = InputUtil.input("Todo List (x jika batal)");

        if (list.equals("x")){
            //batal
        } else {
            todolistService.addTodoList(list);
        }
    }

    public void removeTodoList(){
        System.out.println("MENGHAPUS TODO LIST");

        var number = InputUtil.input("Nomor yang dihapus (x jika batal) ");

        if (number.equals("x")){
            //batal
        } else {
           todolistService.removeTodoList(Integer.valueOf(number));
        }
    }
}
