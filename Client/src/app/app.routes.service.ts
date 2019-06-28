import { ModalsComponent } from "./views/modals/modals.component";
import { BasicTableComponent } from "./views/tables/basic-table/basic-table.component";
import { Profile1Component } from "./views/profile/profile1/profile1.component";
import { RouterModule, Route } from "@angular/router";
import { ModuleWithProviders } from "@angular/core";
import { NotFoundComponent } from "./views/errors/not-found/not-found.component";
import { Dashboard1Component } from "./views/dashboards/dashboard1/dashboard1.component";
import { LoginComponent } from "./views/login/login.component";
import { RegisterComponent } from "./views/register/register.component";
import { StatsCardComponent } from "./views/dashboards/common/stats-card/stats-card.component";

const routes: Route[] = [
  { path: "", pathMatch: "full", redirectTo: "pages/login" },
  { path: "pages/login", component: LoginComponent },
  { path: "pages/register", component: RegisterComponent },
  {
    path: "dashboards",
    children: [
      { path: "v1", component: Dashboard1Component },
      { path: "v2", component: StatsCardComponent }
    ]
  },
  {
    path: "profiles",
    children: [{ path: "profile1", component: Profile1Component }]
  },
  {
    path: "tables",
    children: [{ path: "table1", component: BasicTableComponent }]
  },
  { path: "modals", component: ModalsComponent },
  { path: "**", component: NotFoundComponent }
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
