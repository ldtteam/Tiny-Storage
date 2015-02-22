@echo off

if %1 == dev (
    gradlew -q setDevVersion
    gradlew build --info
) else ( if %1 == patch (
    gradlew -q incrementPatch
    gradlew build --info
    ) else ( if %1 == minor (
        gradlew -q incrementMinor
        gradlew build --info
        ) else ( if %1 == major (
            gradlew -q incrementMajor
            gradlew build --info
			) else ( if %1 == current (
				gradlew build --info
				else (
					echo Can't detect proper args. Exiting ...
					goto end
				)
			)
        )
    )
)
:end
