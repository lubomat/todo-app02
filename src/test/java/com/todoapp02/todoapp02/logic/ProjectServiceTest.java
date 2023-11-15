package com.todoapp02.todoapp02.logic;

import com.todoapp02.todoapp02.TaskConfigurationProperties;
import com.todoapp02.todoapp02.model.ProjectRepository;
import com.todoapp02.todoapp02.model.TaskGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    @Test
    @DisplayName("should throw IllegalStateException when configured to allow just 1 group and the other undone group exist")
    void createGroup_noMultipleGroupsConfig_And_undoneGroupExists_throwsIllegalStateEsception() {
        //given
        TaskGroupRepository mockGroupRepository = groupRepositoryReturning(true);
        // and
        TaskConfigurationProperties mockConfig = configurationReturning(false);
        // system under test
        var toTest = new ProjectService(null, mockGroupRepository, mockConfig);


        //when + then
        /** assertThatThrownBy(() -> toTest.createGroup(LocalDateTime.now(),0))
                    .isInstanceOf(IllegalStateException.class);
        */

        /*
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> toTest.createGroup(LocalDateTime.now(),0));


         assertThatIllegalStateException()
                .isThrownBy(() -> toTest.createGroup(LocalDateTime.now(),0));
        **/

        //when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(),0));

        //then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("one undone group");

    }

    @Test
    @DisplayName("should throw IllegalArgumentException when configuration ok and no projects for a given id")
    void createGroup_configurationOk_And_noProjects_throwsIllegalArgumentException() {
        //given
        var mockRepository = mock(ProjectRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        //and
        TaskConfigurationProperties mockConfig = configurationReturning(true);
        // system under test
        var toTest = new ProjectService(mockRepository, null, mockConfig);

        //when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(),0));

        //then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id not found");

    }

    @Test
    @DisplayName("should throw IllegalArgumentException when configured to allow just 1 group and  projects for a given id")
    void createGroup_noMultipleGroupsConfig_And_noUndoneGroupExists_noProjects_throwsIllegalArgumentException() {
        //given
        var mockRepository = mock(ProjectRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        //and
        TaskGroupRepository mockGroupRepository = groupRepositoryReturning(false);
        //and
        TaskConfigurationProperties mockConfig = configurationReturning(true);
        // system under test
        var toTest = new ProjectService(mockRepository, null, mockConfig);

        //when
        var exception = catchThrowable(() -> toTest.createGroup(LocalDateTime.now(),0));

        //then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id not found");

    }

    private TaskGroupRepository groupRepositoryReturning(boolean value) {
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(value);
        return mockGroupRepository;
    }

    private TaskConfigurationProperties configurationReturning(boolean result) {
        var mockTemplate = mock(TaskConfigurationProperties.Template.class);
        when(mockTemplate.isAllowMultipleTasks()).thenReturn(result);
        var mockConfig = mock(TaskConfigurationProperties.class);
        when(mockConfig.getTemplate()).thenReturn(mockTemplate);
        return mockConfig;
    }


}