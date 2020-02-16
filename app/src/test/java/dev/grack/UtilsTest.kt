package dev.grack

import dev.grack.Utils
import junit.framework.Assert.assertEquals
import org.junit.Test

class UtilsTest {

  @Test
  fun `format date into Wed, 01 January 2020`() {
    assertEquals("Wed, 01 January 2020", Utils.formatDate("2020-01-01"))
  }

  @Test
  fun `format time into 23colon59`() {
    assertEquals("23:59", Utils.formatTime("23:59:00"))
  }

}