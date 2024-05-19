package ru.point.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import ru.point.room.RequestDao
import ru.point.room.RequestDatabase
import ru.point.room.RequestEntity

@RunWith(JUnit4ClassRunner::class)
class RequestDaoTest {
    private lateinit var database: RequestDatabase
    private lateinit var requestDao: RequestDao

    @Before
    fun initDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            RequestDatabase::class.java
        ).build()

        requestDao = database.provideRequestDao()
    }

    @Test
    fun should_get_data_without_errors() {
        val a = requestDao.getAll()
        val testObserver = TestObserver<List<RequestEntity>>()
        a.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @After
    fun clear() {
        database.close()
    }
}