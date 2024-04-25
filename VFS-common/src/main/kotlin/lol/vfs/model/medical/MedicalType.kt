package lol.vfs.model.medical

/**
 * Defines the type of a [Medical] if it refers to a treatment or a test that can be assigned to an [Age] group
 */
enum class MedicalType {
   /**
    * Test medical type
    */
   TEST,

   /**
    * Treatment medical type
    */
   TREATMENT
}