pipeline 
	{
		agent 
		{
			docker 
			{
				image "maven:3.6.3-jdk-11-slim"
			}
		}
		stages 
		{
			stage('Build') 
			{
				steps 
				{
						sh 'mvn -B -DskipTests clean package'
				}
			}
			stage('Test') 
			{
				steps 
				{
					sh 'mvn test'
				}
				post 
				{
					always 
					{
						junit 'target/surefire-reports/*.xml'
					}
				}
			}
		}
	}