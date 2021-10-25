package com.uts.salon.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.uts.salon.Dao.TodoDao;
import com.uts.salon.Dao.UserDao;
//import com.uts.salon.Dao.SalonDao;
//import com.uts.salon.Model.Salon;
import com.uts.salon.Model.Todo;
import com.uts.salon.Model.User;

@Database(entities = {Todo.class, User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    public abstract UserDao userDao();
    //public abstract SalonDao salonDao();

}
