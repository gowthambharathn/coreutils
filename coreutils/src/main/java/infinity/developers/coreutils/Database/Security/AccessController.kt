package infinity.developers.coreutils.Database.Security

/**
 * Access Controller
 * Handles role-based permissions for tables/actions
 */
object AccessController {

    enum class Role {
        ADMIN,
        MANAGER,
        USER,
        GUEST
    }

    enum class Action {
        CREATE,
        READ,
        UPDATE,
        DELETE,
        EXPORT,
        IMPORT
    }

    private val permissions = mapOf(

        Role.ADMIN to setOf(
            Action.CREATE,
            Action.READ,
            Action.UPDATE,
            Action.DELETE,
            Action.EXPORT,
            Action.IMPORT
        ),

        Role.MANAGER to setOf(
            Action.CREATE,
            Action.READ,
            Action.UPDATE,
            Action.EXPORT
        ),

        Role.USER to setOf(
            Action.READ,
            Action.UPDATE
        ),

        Role.GUEST to setOf(
            Action.READ
        )
    )

    /** Check if role can perform action */
    fun canAccess(
        role: Role,
        action: Action
    ): Boolean {
        return permissions[role]?.contains(action) == true
    }

    /** Require permission or throw error */
    fun requireAccess(
        role: Role,
        action: Action
    ) {
        if (!canAccess(role, action)) {
            throw SecurityException(
                "$role cannot perform $action"
            )
        }
    }

    /** Get allowed actions */
    fun getPermissions(
        role: Role
    ): Set<Action> {
        return permissions[role] ?: emptySet()
    }
}