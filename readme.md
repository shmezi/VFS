VFS - Vaccines for students

Backend endpoints:

Authentication endpoints:

```
/auth/
```

| Name     | Type | Data                                                                              | Description            | 
|----------|------|-----------------------------------------------------------------------------------|------------------------|
| login    | POST | [LoginRequest](VFS-common/src/main/kotlin/lol/vfs/requests/LoginRequest.kt)       | Authenticate yourself  |
| register | POST | [RegisterRequest](VFS-common/src/main/kotlin/lol/vfs/requests/RegisterRequest.kt) | Register a new account |
| logout   | POST | None                                                                              | Log yourself out       |

```
/teacher/
```

| Name    | Type | Data                                                      | Description |
|---------|------|-----------------------------------------------------------|-------------|
| Classes | GET  | [RegisterRequest](VFS-common/src/main/kotlin/lol/vfs/db/) |             |

```

```

| Name | Type | Data | Description |
|------|------|------|-------------|
|      |      |      |             |