<b>Przeglądarka wiadomości</b>

Projekt maven'owy zawierający trzy moduły

client - prosta libka służąca do komunikacji z NewsApi poprzez interfejs NewsApiClient
core - aplikacja spring boot'owa posiadająca NewsController który wystawia endpoint /news/{country}/{category} 
ui - prosta aplikacja frontendowa która zawiera wygenerowane api poprzez "ng-swagger-gen"

sposób zbudowania aplikacji

maven clean install


maven clean install
cd core
docker build -t core .
cd ui
npm install
docker build -t ui-image .

Upewniamy się ze posiadamy dwa obrazy
docker images

odpalamy obrazy
docker run -it -p 4200:4200 ui-image
docker run -it -p 18080:18080 core