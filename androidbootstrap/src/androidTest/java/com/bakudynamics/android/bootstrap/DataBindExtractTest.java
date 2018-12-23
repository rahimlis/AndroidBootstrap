package com.bakudynamics.android.bootstrap;

import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;

import com.bakudynamics.android.bootstrap.factories.IntentFactory;
import com.bakudynamics.android.bootstrap.views.Extractor;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)

public class DataBindExtractTest {

    @Test
    public void testGetFloat() {
        float a = 1.3f;

        String tag = "my_tag";
        Intent intent = IntentFactory.builder().with(tag, a).create();


        assertEquals(intent.getFloatExtra(tag, 0), a, 0);

        Extractor extractor = () -> intent;

        assertNotNull(extractor.getIntent());

        assertTrue(extractor.getIntent().hasExtra(tag));

        assertEquals("Checking float extractor works correctly", a,
                extractor.getFloat(tag, 0), 0);

    }
}
