SELECT * 
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
ORDER BY 3, 4

-- Filtering data that we will use
SELECT location, date, total_cases, new_cases, total_deaths, population
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
ORDER BY 1, 2

-- Total Cases vs Population
-- Shows what percentage of population caught covid in Pakistan
SELECT location, date, population, total_cases, (total_cases/population)*100 AS CasePercentagePerPopulation
FROM PortfolioProject..CovidDeaths
WHERE location = 'Pakistan'
ORDER BY 1, 2

-- Total Cases vs Total Deaths
-- Shows likelihood of dying if you catch covid in Pakistan
SELECT location, date, total_cases, total_deaths, (total_deaths/total_cases)*100 AS DeathPercentage
FROM PortfolioProject..CovidDeaths
WHERE location = 'Pakistan'
ORDER BY 1, 2

-- Countries with highest infection rate compared to population
SELECT location, population, MAX(total_cases) AS HighestInfectionCount, MAX((total_cases/population))*100 AS CasePercentagePerPopulation
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
GROUP BY location, population
ORDER BY CasePercentagePerPopulation DESC

-- Countries with highest death count per population
SELECT location, MAX(cast(total_deaths AS int)) AS TotalDeathCount
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
GROUP BY location
ORDER BY TotalDeathCount DESC

-- Continents with the highest death count per population
SELECT continent, MAX(cast(total_deaths AS int)) AS TotalDeathCount
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
GROUP BY continent
ORDER BY TotalDeathCount DESC

-- Total Cases and Total Deaths per day
SELECT date, SUM(new_cases) as TotalCases, SUM(cast(new_deaths as int)) as TotalDeaths, SUM(cast(new_deaths as int))/SUM(new_cases)*100 as DeathPercentage
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
GROUP BY date
ORDER BY 1, 2

-- Total Cases and Total Deaths so far
SELECT SUM(new_cases) as TotalCases, SUM(cast(new_deaths as int)) as TotalDeaths, SUM(cast(new_deaths as int))/SUM(new_cases)*100 as DeathPercentage
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
ORDER BY 1, 2

                                         ---------------------------------------------------

-- Total Population vs Vaccinations
SELECT death.continent, death.location, death.date, death.population, vacc.new_vaccinations, SUM(CONVERT(bigint, vacc.new_vaccinations)) OVER 
(PARTITION BY death.location ORDER BY death.location, death.date) AS PeopleVaccinatedCounter
FROM PortfolioProject..CovidDeaths death
JOIN PortfolioProject..CovidVaccinations vacc
ON death.location = vacc.location AND 
   death.date = vacc.date
WHERE death.continent is not null
ORDER BY 2, 3


-- USE CTE
WITH PopvsVacc (Continent, Location, Date, Population, New_Vaccinations, PeopleVaccinatedCounter)
AS
(
SELECT death.continent, death.location, death.date, death.population, vacc.new_vaccinations, 
SUM(CONVERT(bigint, vacc.new_vaccinations)) OVER (PARTITION BY death.location ORDER BY death.location, death.date) AS PeopleVaccinatedCounter
FROM PortfolioProject..CovidDeaths death
JOIN PortfolioProject..CovidVaccinations vacc
ON death.location = vacc.location AND 
   death.date = vacc.date
WHERE death.continent is not null
)
SELECT *, (PeopleVaccinatedCounter/Population)*100
FROM PopvsVacc


-- Temp Table to perform Calculation on PARTITION BY in previous query
DROP Table IF exists PercentPopulationVaccinated
CREATE TABLE PercentPopulationVaccinated
(
Continent nvarchar(255),
Location nvarchar(255),
Date datetime,
Population numeric,
New_vaccinations numeric,
VaccinatedCounter numeric
)

INSERT INTO PercentPopulationVaccinated
SELECT death.continent, death.location, death.date, death.population, vacc.new_vaccinations,
SUM(CONVERT(bigint,vacc.new_vaccinations)) OVER (PARTITION BY death.Location ORDER BY death.location, death.Date) AS VaccinatedCounter
FROM PortfolioProject..CovidDeaths death
JOIN PortfolioProject..CovidVaccinations vacc
	ON death.location = vacc.location
	AND death.date = vacc.date

SELECT *, (VaccinatedCounter/Population)*100
FROM PercentPopulationVaccinated

--                                                         Create View For Visualizations
-- #1:
CREATE VIEW PercentPopulationVaccinated# AS
SELECT death.continent, death.location, death.date, death.population, vacc.new_vaccinations,
SUM(CONVERT(bigint,vacc.new_vaccinations)) OVER (PARTITION BY death.Location ORDER BY death.location, death.Date) AS VaccinatedCounter
FROM PortfolioProject..CovidDeaths death
JOIN PortfolioProject..CovidVaccinations vacc
	ON death.location = vacc.location
	AND death.date = vacc.date
WHERE death.continent IS NOT NULL

-- #2: Continents with the highest death count per population
CREATE VIEW ContinentDeathCount AS
SELECT continent, MAX(cast(total_deaths AS int)) AS TotalDeathCount
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
GROUP BY continent

-- #3: Countries with highest infection rate compared to population
CREATE VIEW CountriesInfectionRate AS
SELECT location, population, MAX(total_cases) AS HighestInfectionCount, MAX((total_cases/population))*100 AS CasePercentagePerPopulation
FROM PortfolioProject..CovidDeaths
WHERE continent is not null
GROUP BY location, population

