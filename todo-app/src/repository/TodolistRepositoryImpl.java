package repository;

import entity.Todolist;

public class TodolistRepositoryImpl implements TodolistRepository{
    public Todolist[] data = new Todolist[10];

    public boolean isFull(){
        //cek apakah ada indeks yang masih kosong
        var isFull = true;
        for (int i = 0; i < data.length; i++) {
            if(data[i] == null){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public void resizeIfFull(){
        //jika lists sudah penuh, resize lists ke 2x ukuran semula
        if (isFull()){
            var temp = data;
            data = new Todolist[data.length * 2];

            for (int i = 0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }
    }
    @Override
    public Todolist[] getAll() {
        return data;
    }

    @Override
    public void add(Todolist todolist) {
        this.resizeIfFull();

        //masukkan list ke array list
        for (int i = 0; i < data.length; i++) {
            if(data[i] == null){
                data[i] = todolist;
                break;
            }
        }
    }

    @Override
    public boolean remove(Integer number) {
        if((number - 1) >= data.length){
            return false;
        } else if(data[number -1] == null){
            return false;
        } else {
            for (int i = (number - 1); i < data.length ; i++) {
                if(i == (data.length - 1)){
                    data[i] = null;
                } else {
                    data[i] = data[i + 1];
                }
            }
            return true;
        }
    }
}
